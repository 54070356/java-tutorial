 
package laofuzi.java.core.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

 

/**
 * 
 *
 */
public class SourceCodeJavaCompiler {   
    static final String JAVA_EXTENSION = ".java";
    // 存放javac命令对应的选项，比如‘-target’ ‘1.8’
    private final List<String> options;
    // 真正执行编译过程的编译器
    private final JavaCompiler compiler;
    // 自定义实现，用于管理编译相关文件
    private final FileManagerImpl javaFileManager;
    // 诊断信息收集器
    private DiagnosticCollector<JavaFileObject> diagnostics;
    // 自定义类加载器
    private final ClassLoaderImpl classLoader;

    /**
     * 
     */
    public SourceCodeJavaCompiler(ClassLoader parentLoader, List<String> options) {
        this.options = options;
        // 使用系统默认的编译器
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.classLoader = new ClassLoaderImpl(parentLoader);
        this.diagnostics = new DiagnosticCollector<JavaFileObject>();
        JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        this.javaFileManager = new FileManagerImpl(fileManager, classLoader);
    }

    public Class<?> compile(String sourceCode) throws Exception {
        String classFullName = getFullClassName(sourceCode);
        return compile(classFullName, sourceCode);
    }

    // 编译方法
    private Class<?> compile(String classFullName, String sourceCode) throws Exception {

        /*
         * 首先将sourceCode转换成CompilerAPI中对应的抽象表示，然后放入javaFileManager管理
         */
        int dotPos = classFullName.lastIndexOf('.');
        String className = dotPos == -1 ? classFullName : classFullName.substring(dotPos + 1);
        String packageName = dotPos == -1 ? "" : classFullName.substring(0, dotPos);
        JavaFileObjectImpl javaFileObject = new JavaFileObjectImpl(className, sourceCode);
        javaFileManager.putFileForInput(StandardLocation.SOURCE_PATH, packageName, className + JAVA_EXTENSION,
                javaFileObject);
        /*
         * 执行编译过程
         */
        List<JavaFileObject> sources = new ArrayList<JavaFileObject>();
        sources.add(javaFileObject);
        // 获取Task并执行
        CompilationTask task = compiler.getTask(null, javaFileManager, diagnostics, options, null, sources);
        Boolean result = task.call();
        if (!result) {
            List<Diagnostic<? extends JavaFileObject>> list = diagnostics.getDiagnostics();
            for (Diagnostic<? extends JavaFileObject> diagnostic : list) {
            	System.out.println(diagnostic.toString()); 
            }
        }
        /*
         * 获取Class实例
         */
        return classLoader.loadClass(classFullName);

    }

    /**
     * 获取类的全名称
     *
     * @param sourceCode
     *            源码
     * @return 类的全名称
     */
    public static String getFullClassName(String sourceCode) {
        String className = "";
        Pattern pattern = Pattern.compile("package\\s+\\S+\\s*;");
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            className = matcher.group().replaceFirst("package", "").replace(";", "").trim() + ".";
        }

        pattern = Pattern.compile("class\\s+\\S+\\s+\\{"); 
        matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            className += matcher.group().replaceFirst("class", "").replace("{", "").trim();
        }
        System.out.println(className); 
        return className;
    }
}
