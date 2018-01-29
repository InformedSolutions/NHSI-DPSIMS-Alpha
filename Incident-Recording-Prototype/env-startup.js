require('dotenv').config();
var env = process.env.NODE_ENV || 'development';
if(env === "production"){
    require('./server');
} else {
   require('./start');
}