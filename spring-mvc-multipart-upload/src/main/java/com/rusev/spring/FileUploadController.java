package com.rusev.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static final String TARGET_FOLDER=  "/home/rusev/temp/";

    @RequestMapping(method = RequestMethod.GET)
    public String handleGetRequest() {
        return "file-upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handlePostRequest(MultipartHttpServletRequest request,
                                    Model model) throws IOException {

        MultipartFile multipartFile = request.getFile("user-file");
        String name = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();

        Files.copy(inputStream, Paths.get(TARGET_FOLDER + name), StandardCopyOption.REPLACE_EXISTING);
        model.addAttribute("msg", "File has been uploaded:  " + name);
        return "response";
    }
}