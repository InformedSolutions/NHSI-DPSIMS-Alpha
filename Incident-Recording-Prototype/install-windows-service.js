var Service = require('node-windows').Service;

// Create a new service object
var svc = new Service({
    name:'Incident Recording Prototype',
    description: 'Prototype created for Alpha phase of the NHSI DPSIMS project.',
    script: 'server.js'
});

// Listen for the "install" event, which indicates the
// process is available as a service.
svc.on('install',function(){
    svc.start();
});

svc.install();
