/*
 * Copyright (C) 2021 bsyonline
 */

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author rolex
 * @since 2021
 */
public class Main {
    public static void main(String[] args) throws IOException {

        list("D:/Dev/IdeaProjects/AlphaX", new String[]{".git"});

        Collection<File> files = FileUtils.listFiles(new File("D:/Dev/IdeaProjects/AlphaX"), null, true);
        for (File file : files) {
            if(!file.getPath().contains(".git") && !file.getPath().contains(".idea")) {
                String s = FileUtils.readFileToString(new File(file.getPath()));
                String replace = s.replace("alphax", "alphax");
                FileUtils.writeStringToFile(new File(file.getPath()), replace, "UTF-8");
            }
        }
    }

    public static void list(String path, String[] exclude) throws IOException {
        File file = new File(path);
        if (file.isDirectory() && file.getPath().endsWith("alphax")) {
            FileUtils.copyDirectory(new File(file.getPath()), new File(file.getPath().replace("alphax","alphax")));
            FileUtils.deleteDirectory(new File(file.getPath()));
        }
        String[] children = file.list();
        if (children != null && children.length > 0) {
            for (String f : children) {
                if(Arrays.asList(exclude).contains(f)){
                    continue;
                }
                list(path + File.separator + f, exclude);
            }
        }

    }


}
