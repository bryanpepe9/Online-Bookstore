package shoppingcart.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shoppingcart.dao.ProductDAO;
import shoppingcart.entity.Product;
import shoppingcart.form.ProductForm;

@Component
public class ProductFormValidator implements Validator {
 
   @Autowired
   private ProductDAO productDAO;
 
   // This validator only checks for the ProductForm.
   @Override
   public boolean supports(Class<?> clazz) {
      return clazz == ProductForm.class;
   }
 
   @Override
   public void validate(Object target, Errors errors) {
      ProductForm productForm = (ProductForm) target;
 
      // Check the fields of ProductForm.
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookID", "NotEmpty.productForm.bookID");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
 
      String bookID = productForm.getBookID();
      if (bookID != null && bookID.length() > 0) {
         if (bookID.matches("\\s+")) {
            errors.rejectValue("bookID", "Pattern.productForm.bookID");
         } else if (productForm.isNewProduct()) {
            Product product = productDAO.findProduct(bookID);
            if (product != null) {
               errors.rejectValue("bookID", "Duplicate.productForm.bookID");
            }
         }
      }
   }
 
}
