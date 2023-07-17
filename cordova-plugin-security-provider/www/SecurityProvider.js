var exec = require('cordova/exec');

exports.providerInstallerCheck = function(success, error) {
    exec(success, error, "SecurityProvider", "providerInstallerCheck", []);
};
