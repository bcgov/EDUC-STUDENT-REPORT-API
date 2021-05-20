package ca.bc.gov.educ.grad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradRequirement implements Serializable {
    String rule;
    String description;
}
