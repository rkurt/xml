package pl.rkurt.xml.controllers;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.rkurt.xml.parser.ParseException;
import pl.rkurt.xml.parser.Parser;
import pl.rkurt.xml.products.ProductType;
import pl.rkurt.xml.services.XMLService;
import pl.rkurt.xml.validator.ValidationException;

import java.io.IOException;

import static pl.rkurt.xml.parser.Parser.SELECT_FILE;

@Controller
public class IndexController {

    private final XMLService XMLService;
    private static final String INDEX = "index";
    private static final String LIST = "list";
    private static final String REDIRECT = "redirect:/";
    private static final String MESSAGE = "message";

    @Autowired
    public IndexController(XMLService XMLService) {
        this.XMLService = XMLService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("productTypes", ProductType.values());
        return INDEX;
    }

    @GetMapping("/list")
    public String list() {
        return LIST;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, @NotNull @RequestParam("productType") ProductType productType,
                         ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (Parser.checkFile.test(file)) {
            redirectAttributes.addFlashAttribute(MESSAGE, SELECT_FILE);
            return REDIRECT;
        }

        try {
            XMLService.validateProducts(file.getInputStream(), productType);
            modelMap.addAttribute("products", XMLService.parseProducts(file.getInputStream(), productType));
            modelMap.addAttribute("productType", productType);
            return LIST;
        } catch (IOException | ParseException | ValidationException e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());
            return REDIRECT;
        }
    }
}
