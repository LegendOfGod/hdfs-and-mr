package product_order_2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import product_order_1.ReduceJoinMainJob;

import java.net.URI;

/**
 * @author lqb
 * @date 2021/10/9 11:46
 */
public class MapperJoinMainJob extends Configured implements Tool {

    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), MapperJoinMainJob.class.getSimpleName());
        job.setJarByClass(MapperJoinMainJob.class);
        //输入
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("hdfs://172.24.250.142:8020/product_order"));
        //缓存
        job.addCacheFile(new URI("hdfs://172.24.250.142:8020/cache_file/product.txt"));
        //mapper
        job.setMapperClass(MapperJoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //reducer
        job.setReducerClass(MapperJoinReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //输出
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("hdfs://172.24.250.142:8020/product_order_out"));
        boolean completion = job.waitForCompletion(true);
        return completion ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        MapperJoinMainJob job = new MapperJoinMainJob();
        int run = ToolRunner.run(configuration, job, args);
        System.exit(run);
    }
}
