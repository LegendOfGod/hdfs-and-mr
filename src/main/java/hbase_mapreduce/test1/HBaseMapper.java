package hbase_mapreduce.test1;

import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * @author lqb
 * @date 2021/10/29 9:00
 */
public class HBaseMapper extends TableMapper<Text, Put> {
    /**
     * @param key hbase 表的行键
     * @param value cell
     * @param context 上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Mapper<ImmutableBytesWritable, Result, Text, Put>.Context context) throws IOException, InterruptedException {
        byte[] rowKey = key.get();
        Put put = new Put(rowKey);
        List<Cell> cells = value.listCells();
        for (Cell cell : cells) {
            //判断列簇和列名
            byte[] family = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            if ("f1".equals(Bytes.toString(family))){
                if ("name".equals(Bytes.toString(qualifier)) || "age".equals(Bytes.toString(qualifier))){
                    put.add(cell);
                }
            }
        }
        if (!put.isEmpty()){
            context.write(new Text(Bytes.toString(rowKey)),put);
        }
    }
}
