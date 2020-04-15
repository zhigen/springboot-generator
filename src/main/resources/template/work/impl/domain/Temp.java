package {packageName};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
{importString}
/**
 * {tableComment}
 *
 * @author {author}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("{tableName}")
public class {className} extends Model<{className}> {
    @TableId(value = "id", type = IdType.AUTO)
{fieldString}
}
