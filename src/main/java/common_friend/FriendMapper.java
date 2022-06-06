package common_friend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/9 14:11
 */
public class FriendMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(":");
        String userStr = split[0];
        String[] split1 = split[1].split(",");
        for (String s : split1) {
            context.write(new Text(s),new Text(userStr));
        }
    }
}
