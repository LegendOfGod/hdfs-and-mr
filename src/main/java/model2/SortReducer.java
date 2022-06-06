package model2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 15:36
 */
public class SortReducer extends Reducer<PairWritable, IntWritable, Text,IntWritable> {

    private Text outputKey = new Text();

    @Override
    protected void reduce(PairWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("reduce:"+key.toString());
        for (IntWritable value : values) {
            System.out.println("reduce:"+key.toString()+value);
            outputKey.set(key.getFirst());
            context.write(outputKey,value);
        }
    }
}
