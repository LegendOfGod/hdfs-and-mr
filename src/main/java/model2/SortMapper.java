package model2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 15:29
 */
public class SortMapper extends Mapper<LongWritable, Text, PairWritable, IntWritable> {
    private PairWritable pairWritable = new PairWritable();
    private IntWritable intWritable = new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lines = value.toString();
        String[] split = lines.split(" ");
        pairWritable.set(split[0],Integer.parseInt(split[1]));
        intWritable.set(Integer.parseInt(split[1]));
        System.out.println("mapper:"+pairWritable.toString());
        System.out.println(pairWritable.hashCode());
        context.write(pairWritable, intWritable);
    }
}
