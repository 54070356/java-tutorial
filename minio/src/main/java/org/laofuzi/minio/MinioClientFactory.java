package org.laofuzi.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

public class MinioClientFactory {
    public static MinioClient createClient() {
        MinioClient client = null;
        try {
            client = new MinioClient("https://127.0.0.1:9000", "minio", "minioadmin", false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return client;
    }
}
