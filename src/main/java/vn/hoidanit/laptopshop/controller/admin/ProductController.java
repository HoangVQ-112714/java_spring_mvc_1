package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private UploadService uploadService;

    private ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product/list")
    public String getProductList(Model model) {
        List<Product> arrProduct = this.productService.getAllProducts();
        model.addAttribute("products", arrProduct);
        return "admin/product/product-list";
    }

    @GetMapping("/admin/product/create")
    public String createProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/product-create";
    }

    @PostMapping("/admin/product/list")
    public String createProduct(@ModelAttribute("newProduct") Product product,
                                @RequestParam("hoaquaFile") MultipartFile file) {
        String image = this.uploadService.handleSaveFileUploadFile(file, "product");
        product.setImage(image);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product/list";
    }
}
