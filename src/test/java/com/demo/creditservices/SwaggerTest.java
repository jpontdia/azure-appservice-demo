package com.demo.creditservices;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerTest {

    @LocalServerPort
    int port;

    private static String URLReader(URL url, Charset encoding) throws IOException {
        try (InputStream in = url.openStream()) {
            byte[] bytes = in.readAllBytes();
            return new String(bytes, encoding);
        }
    }

    @BeforeEach
    void initEach() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Validate the generation of swagger specification")
    void validateSwaggerGeneration() throws Exception {
        String swaggerSchemaUrl = String.format("http://localhost:%d/v3/api-docs", port);
        String generatedSwagger = URLReader(new URL(swaggerSchemaUrl), Charset.defaultCharset());
        log.debug("Generated swagger:\n{}", generatedSwagger);
        writeFile(generatedSwagger);
        assertThat(generatedSwagger).contains("openapi");
    }

    private void writeFile(String content) {

        String swaggerDirPath = "target/generated-sources/swagger";
        File theDir = new File(swaggerDirPath);

        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (SecurityException ignored) {
            }
        }

        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(swaggerDirPath + "/" + "swagger.json");
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}