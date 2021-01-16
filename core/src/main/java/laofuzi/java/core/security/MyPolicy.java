package laofuzi.java.core.security;

import java.net.URL;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.Map;

public class MyPolicy extends Policy {
    //custom classes with policy mapping
    private final Map<String, Policy> plugins;

    public MyPolicy(Map<String, Policy> plugins) {
        this.plugins = plugins;
    }

    @Override
    public boolean implies(ProtectionDomain domain, Permission permission) {
        CodeSource codeSource = domain.getCodeSource();
        if (codeSource == null) {
            return false;
        }

        URL location = codeSource.getLocation();
        if (location != null) {
            //get the custom plugin policy rules and validate the permissions
            Policy plugin = this.plugins.get(location.getFile());
            if (plugin != null) {
                return plugin.implies(domain, permission);
            }
        }
        return defaultSystemPermissions().implies(permission);
    }
    private PermissionCollection defaultSystemPermissions() {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission()); // this will set the application default permissions, in there we enable all 
        return permissions;
    }
}
