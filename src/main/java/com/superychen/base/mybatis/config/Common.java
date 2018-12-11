package com.superychen.base.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Common {

    static SqlSessionFactory getSqlSessionFactory(String schemaName, SqlSessionFactoryBean sessionFactoryBean, String mapperDiyLocation) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(mapperDiyLocation);
            sessionFactoryBean.setMapperLocations(resources);
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("配置{}的SqlSessionFactory失败，error:{}", schemaName, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String extractSchemaName(String url) {
        return url.split("/")[3].split("\\?")[0];
    }

    static String firstUpper(String origin) {
        return origin.substring(0, 1).toUpperCase() + origin.substring(1).toLowerCase();
    }

    public static String generateCode(String url, String username, String password) {
        //提取数据库名称：jdbc:mysql://localhost:3306/base
        String schemaName = extractSchemaName(url);
        String classPrefix = firstUpper(schemaName);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("template.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String template = sb.toString();
            template = template.replaceAll("#URL", url);
            template = template.replaceAll("#USERNAME", username);
            template = template.replaceAll("#PASSWORD", password);
            template = template.replaceAll("#CLASSPREFIX", classPrefix);
            template = template.replaceAll("#SCHEMANAME", schemaName);
            return template;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(generateCode("jdbc:mysql://localhost:3306/test", "root", "root"));
//    }

}
