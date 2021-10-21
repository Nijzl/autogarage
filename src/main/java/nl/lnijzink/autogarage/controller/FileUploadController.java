package nl.lnijzink.autogarage.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import nl.lnijzink.autogarage.storage.StorageFileNotFoundException;
import nl.lnijzink.autogarage.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

/*
package nl.lnijzink.autogarage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @GetMapping
    public String main() {
        return "UploadView";
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() +
"!");

        return "redirect:/";
    }
//}
//@Controller
//public class FileUploadController {
//    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
//
//    @GetMapping("/")
//    public String UploadPage(Model model){
//        return "UploadView";
//    }
//
//    @PostMapping("/")
//    public String upload(Model model, @RequestParam("files") MultipartFile[] files){
//        StringBuilder fileNames = new StringBuilder();
//        for(MultipartFile file : files){
//            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
//            fileNames.append(file.getOriginalFilename());
//            try {
//                Files.write(fileNameAndPath, file.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        model.addAttribute("msg", "Successfully uploaded files: "+fileNames.toString());
//        return "UploadStatusView";
//    }
}
*/
