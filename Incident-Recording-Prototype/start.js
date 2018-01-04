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

var env = process.env.PATH + "D:\\Program Files (x86)\\SiteExtensions\\Kudu\\69.61204.3166\\bin\\NativeBinaries/x86;D:\\home\\site\\deployments\\tools;D:\\Program Files (x86)\\SiteExtensions\\Kudu\\69.61204.3166\\bin\\Scripts;D:\\Program Files (x86)\\MSBuild\\14.0\\Bin;D:\\Program Files (x86)\\Git\\cmd;D:\\Program Files (x86)\\Microsoft Visual Studio 11.0\\Common7\\IDE\\CommonExtensions\\Microsoft\\TestWindow;D:\\Program Files (x86)\\Microsoft SQL Server\\110\\Tools\\Binn;D:\\Program Files (x86)\\Microsoft SDKs\\F#\\3.1\\Framework\\v4.0;D:\\Program Files (x86)\\Git\\bin;D:\\Program Files (x86)\\Git\\usr\\bin;D:\\Program Files (x86)\\Git\\mingw32\\bin;D:\\Program Files (x86)\\nodejs\\0.10.28;D:\\Program Files (x86)\\npm\\1.4.28;C:\\DWASFiles\\Sites\\#1psims-incident-recording\\AppData\\npm;D:\\Program Files (x86)\\bower\\1.7.9;D:\\Program Files (x86)\\grunt\\0.1.13;D:\\Program Files (x86)\\gulp\\3.9.0.1;D:\\Program Files (x86)\\funcpack\\0.2.2;D:\\Program Files (x86)\\nodejs;D:\\Windows\\system32;D:\\Windows;D:\\Windows\\System32\\Wbem;D:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;D:\\Users\\Administrator\\AppData\\Local\\Microsoft\\WindowsApps;;D:\\Program Files (x86)\\dotnet;D:\\Windows\\system32\\config\\systemprofile\\AppData\\Local\\Microsoft\\WindowsApps;D:\\Program Files (x86)\\Git\\cmd;D:\\Program Files (x86)\\PHP\\v5.6;D:\\Python27;";
console.log(env);

process.env['FORCE_COLOR'] = 1
var gulp = spawn('gulp', {env: env})
gulp.stdout.pipe(process.stdout)
gulp.stderr.pipe(process.stderr)
process.stdin.pipe(gulp.stdin)

gulp.on('exit', function (code) {
  console.log('gulp exited with code ' + code.toString())
})
