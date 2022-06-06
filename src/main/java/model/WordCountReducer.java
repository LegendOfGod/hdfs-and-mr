package model;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 11:32
 */
public class WordCountReducer extends Reducer<Text, LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println(key.toString());
        long count = 0;
        for (LongWritable value : values) {
            System.out.println(value);
            count += value.get();
        }
        context.write(key,new LongWritable(count));
    }
}
