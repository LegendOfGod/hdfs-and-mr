package hbase_mapreduce.test2;

import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/29 10:54
 */
public class HdfsReducer extends TableReducer<Text, NullWritable, ImmutableBytesWritable> {
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Reducer<Text, NullWritable, ImmutableBytesWritable, Mutation>.Context context) throws IOException, InterruptedException {
        String[] split = key.toString().split("\\s+");
        Put put = new Put(split[0].getBytes());
        put.addColumn("f1".getBytes(),"name".getBytes(),split[1].getBytes());
        put.addColumn("f1".getBytes(),"age".getBytes(),split[2].getBytes());
        context.write(new ImmutableBytesWritable(split[0].getBytes()),put);
    }
}
