package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Agent {

    @Id
    @NotBlank(message = "Identifier must not be blank.")
    @Size(max = 25, message = "Identifier must be less than 25 characters.")
    private String identifier;
    
    @NotBlank(message = "First name must not be empty.")
    @Size(max = 25, message = "First name must be less than 25 characters.")
    private String firstName;
   
    @Size(max = 25, message = "Middle name must be less than 25 characters.")
    private String middleName;
   
    @NotBlank(message = "Last name must not be empty.")
    @Size(max = 25, message = "Last name must be less than 25 characters.")
    private String lastName;
    private String pictureUrl;
   
    
    @NotNull(message = "Birth date must not be null.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @NotNull(message = "Height cannot be null.")
    @Min(value = (36), message = "Min height is 36 inches.")
    @Max(value = (96), message = "Max height is 96 inches.")
    private Integer height;
    
    @PastOrPresent(message = "Activation date must be today's date.")
    @NotNull(message = "Activation date must not be null.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;
    
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    private SecurityClearance securityClearance;

}
