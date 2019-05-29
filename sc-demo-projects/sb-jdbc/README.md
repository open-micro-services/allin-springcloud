# Technologies

SpringBoot+ Web + Freemarker+JDBC(Mysql)

# Features

## JdbcTemplate to operation Mysql database

`    @Autowired(required = true)    JdbcTemplate jdbcTemplate;
    
    @GetMapping("/list")
    @ResponseBody
    public List<Map<String, Object>> query() {
        System.out.println("" + jdbcTemplate.getDataSource());
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from xht_events order by xiancode limit 0,10");
        return list;
    }
`


