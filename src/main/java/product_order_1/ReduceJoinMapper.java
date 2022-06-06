package product_order_1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/9 10:40
 */
public class ReduceJoinMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        String fileName = inputSplit.getPath().getName();
        if ("product.txt".equals(fileName)){
            String[] split = value.toString().split(",");
            context.write(new Text(split[0]),value);
        }else {
            String[] split = value.toString().split(",");
            context.write(new Text(split[2]),value);
        }

    }
}
