package product_order_2;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

/**
 * @author lqb
 * @date 2021/10/9 11:24
 */
public class MapperJoinMapper extends Mapper<LongWritable, Text,Text,Text> {

    private HashMap<String, String> map  = new HashMap<String, String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //maptask节点分布式缓存文件列表
        URI[] cacheFiles = context.getCacheFiles();
        //获取FileSystem
        FileSystem fileSystem = FileSystem.get(cacheFiles[0], context.getConfiguration());
        //文件输入流
        FSDataInputStream inputStream = fileSystem.open(new Path(cacheFiles[0]));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            String[] split = line.split("\\s+");
            map.put(split[0],line);
        }
        bufferedReader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\\s+");
        String productId = split[2];
        String productLine = map.get(productId);
        String valueLine = productLine+" "+value.toString();
        context.write(new Text(productId), new Text(valueLine));
    }
}
