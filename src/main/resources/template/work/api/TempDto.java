package {packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
{importString}
/**
 * @author {author}
 */
@Data
@ApiModel("{tableComment}")
public class {className}Dto implements Serializable {
{dtoFieldString}
}
