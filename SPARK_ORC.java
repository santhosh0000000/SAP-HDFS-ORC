import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class DataINTG {
    public static void main(String[] args) {
        String host = "demo.gpd.local";
        String port = "43523";
        String username = "sandy";
        String password = "@^%RYFTR#Y%^R";
        String url = "jdbc:sap://" + host + ":" + port;
        String schema = "MONKLPD";
        String tableName = "PRODELEMENTS";

        SparkSession spark = SparkSession
                .builder()
                .appName("SparkConnector")
                .master("local[*]")  // Use all available cores for local execution
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .format("jdbc")
                .option("url", url)
                .option("user", username)
                .option("password", password)
                .option("dbtable", tableName)  // Use only the table name here
                .option("currentSchema", schema)  // Set the default schema for this JDBC session
                .load();

        // Specify the Hadoop Distributed File System (HDFS) output path
        String hdfsOutputPath = "hdfs://192.168.247.6:8020/TEST_PLT/" + tableName;

        df.write().mode(SaveMode.Overwrite).option("header", "true").orc(hdfsOutputPath);

        spark.stop();
    }
}
