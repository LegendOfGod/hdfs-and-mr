package product_order_1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/9 10:50
 */
public class ReduceJoinReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String first = "";
        String second = "";
        for (Text value : values) {
            if (value.toString().startsWith("P")) {
                first = value.toString();
            } else {
                second += value.toString();
            }
        }
        context.write(key, new Text(first + " " + second));
    }
}
