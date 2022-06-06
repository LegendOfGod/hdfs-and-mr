package common_friend_2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author lqb
 * @date 2021/10/9 14:39
 */
public class Friend2Mapper extends Mapper<LongWritable,Text, Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\\s+");
        String friendStr = split[1];
        String[] userArray = split[0].split("-");
        Arrays.sort(userArray);
        for (int i = 0; i < userArray.length - 1; i++) {
            for (int j = i + 1;j < userArray.length;j++){
                context.write(new Text(userArray[i]+"-"+userArray[j]),new Text(friendStr));
            }
        }
    }
}
