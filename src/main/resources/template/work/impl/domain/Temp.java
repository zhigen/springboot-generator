package {packageName};

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
{importString}
/**
 * {tableComment}
 *
 * @author {author}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@FieldNameConstants
@Accessors(chain = true)
@TableName("{tableName}")
public class {className} extends Model<{className}> {
    @TableId(value = "id", type = IdType.AUTO)
{fieldString}
}
