
## Master-Slave -WR databases solution

Based on Mysql DataBasses for multiple db solutions.

WR write-read databases to distinguish.

## Spring sources code for DataSource

 classes:
 
    
    InitializingBean 
    
      AbstractDataSource
     
         AbstractRoutingDataSource
 
 
## What should we do
 
 extends AbstractRoutingDataSource and @Override method :
 
 **_determineCurrentLookupKey()_**
     
     public class DBRoutingDataSource extends AbstractRoutingDataSource {
     
         @Override
         protected Object determineCurrentLookupKey() {
             return DBDataSourceHolder.getDbDataSourceHolderType();
         }
     }
 
 
## How to ensure user thread keep or change the DataSource

We use TheadLocal to make sure the DataSource can be changed.

    
    public class DBDataSourceHolder {
    
        private static final ThreadLocal<DataSourceType> dbDataSourceHolder=new ThreadLocal<>();
    
        public static DataSourceType getDbDataSourceHolderType() {
            return dbDataSourceHolder.get();
        }
    
        public static void setDbDataSourceHolder(DataSourceType dataSourceType) {
            dbDataSourceHolder.set(dataSourceType);
        }
    
        public static void master() {
            dbDataSourceHolder.set(DataSourceType.MASTER);
        }
    
        public static void slave() {
            dbDataSourceHolder.set(DataSourceType.SLAVE);
        }
    
    }


 
 