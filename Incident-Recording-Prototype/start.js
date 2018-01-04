// Check for `node_modules` folder and warn if missing

var path = require('path')
var fs = require('fs')

// Check if node_modules folder exists
const nodeModulesExists = fs.existsSync(path.join(__dirname, '/node_modules'))
if (!nodeModulesExists) {
  console.error('ERROR: Node module folder missing. Try running `npm install`')
  process.exit(0)
}

// Create template .env file if it doesn't exist
const envExists = fs.existsSync(path.join(__dirname, '/.env'))
if (!envExists) {
  console.log('Creating template .env file')
  fs.createReadStream(path.join(__dirname, '/lib/template.env'))
  .pipe(fs.createWriteStream(path.join(__dirname, '/.env')))
}

// run gulp

var spawn = require('cross-spawn')

var env = process.env.PATH + "D:\\Program Files (x86)\\gulp\\3.9.0.1";
console.log(env);

process.env['FORCE_COLOR'] = 1
var gulp = spawn('gulp', {env: env})
gulp.stdout.pipe(process.stdout)
gulp.stderr.pipe(process.stderr)
process.stdin.pipe(gulp.stdin)

gulp.on('exit', function (code) {
  console.log('gulp exited with code ' + code.toString())
})
