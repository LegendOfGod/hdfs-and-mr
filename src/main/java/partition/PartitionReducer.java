package partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 16:17
 */
public class PartitionReducer extends Reducer<Text, NullWritable,Text,NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable nullWritable : values) {
            context.write(key,NullWritable.get());
        }
    }
}
