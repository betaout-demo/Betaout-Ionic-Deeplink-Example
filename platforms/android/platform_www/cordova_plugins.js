cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "cordova-plugin-betaout.BetaoutWrapper",
        "file": "plugins/cordova-plugin-betaout/www/BetaoutWrapper.js",
        "pluginId": "cordova-plugin-betaout",
        "clobbers": [
            "cordova.plugins.Betaout"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-betaout": "1.0.0"
};
// BOTTOM OF METADATA
});