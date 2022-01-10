const mysql = require("mysql2");
const connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    port : 3306,
    password : 'mariadb',
    database : 'test'
});

connection.connect();

connection.query('select * from test',(err,rows,fields)=>{
    if(err) throw err;
    console.log('table info is ...');
    console.table(rows);
});

connection.end();