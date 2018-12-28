# Download Redis

https://github.com/MicrosoftArchive/redis/releases

Download: Redis-x64-3.2.100.zip

Unzip to C:/DEVELOPERS/Redis .

# Windows install redis

## Redis by default

**install redis service:**

```
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.windows.conf --service-name redis-default-6379
```

**start redis service:**

```
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis-default-6379
```

**uninstall redis service:**

```
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis-default-6379
```

## Redis by cluster

cluster nodes must has 3 nodes at least.Here are 7 nodes for cluster.

template: redis.*.conf, for example:

```
bind 127.0.0.1
port 7001
loglevel notice
logfile "Logs/redis7001_log.txt"
syslog-enabled yes
syslog-ident redis7001
appendonly yes
appendfilename "appendonly.7001.aof"
cluster-enabled yes
cluster-config-file nodes.7001.conf
cluster-node-timeout 15000
cluster-slave-validity-factor 10
cluster-migration-barrier 1
cluster-require-full-coverage yes
```

**install redis service:**

```
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.7001.conf --service-name redis7001
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.7002.conf --service-name redis7002
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.7003.conf --service-name redis7003
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.7004.conf --service-name redis7004
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.7005.conf --service-name redis7005
C:/DEVELOPERS/Redis/redis-server.exe --service-install C:/DEVELOPERS/Redis/redis.7006.conf --service-name redis7006
```

**start redis service:**

```
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis7001
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis7002
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis7003
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis7004
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis7005
C:/DEVELOPERS/Redis/redis-server.exe --service-start  --service-name redis7006
```

**uninstall redis service:**

```
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis7001
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis7002
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis7003
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis7004
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis7005
C:/DEVELOPERS/Redis/redis-server.exe --service-uninstall  --service-name redis7006
```

# Settings about  Ruby for cluster

## Download and install Ruby 

https://rubyinstaller.org/downloads/

https://github.com/oneclick/rubyinstaller2/releases/download/rubyinstaller-2.5.3-1/rubyinstaller-devkit-2.5.3-1-x64.exe

## Download Ruby redis driver

https://rubygems.org/gems/redis/versions/3.2.2

And you will get :redis-3.2.2.gem

Put it into Ruby_Home path and install:

```
gem install --local $Ruby_Home/redis-3.2.2.gem 
```

## Download redis-trib.rb

https://raw.githubusercontent.com/MSOpenTech/redis/3.0/src/redis-trib.rb

Download and put redis-trib.rb into  C:/DEVELOPERS/Redis/ 

Make sure  it can runnable as Ruby app.

## Cluster operations

cd  C:/DEVELOPERS/Redis/ 

**create cluster**
```
redis-trib.rb create --replicas 0 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006
```
**check cluster**
```
redis-trib.rb check 127.0.0.1:7001
```

**get into redis node**

```
redis-cli.exe -c -p 7001
```
and then you can see the cluster info with

```
cluster info
```


# All installs reference

https://www.cnblogs.com/tommy-huang/p/6240083.html

