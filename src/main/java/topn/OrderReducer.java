package topn;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/11 11:38
 */
public class OrderReducer extends Reducer<OrderBean, Text,Text, NullWritable> {
    @Override
    protected void reduce(OrderBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int i = 0;
        for (Text value : values) {
            context.write(value,NullWritable.get());
            i++;
            if (i >= 1) {
                break;
            }
        }
    }
}
