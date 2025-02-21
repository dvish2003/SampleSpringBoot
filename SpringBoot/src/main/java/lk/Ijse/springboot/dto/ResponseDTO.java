package lk.Ijse.springboot.dto;
/**
 * Author: vishmee
 * Date: 2/7/25
 * Time: 1:33 AM
 * Description:
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    private String code;
    private String message;
    private Object content;
}
