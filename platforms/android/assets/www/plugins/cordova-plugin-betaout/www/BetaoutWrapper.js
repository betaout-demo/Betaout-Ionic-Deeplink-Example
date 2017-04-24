cordova.define("cordova-plugin-betaout.BetaoutWrapper", function(require, exports, module) {
var argscheck = require('cordova/argscheck'),
	channel = require('cordova/channel'),
	utils = require('cordova/utils'),
	exec = require('cordova/exec'),
	cordova = require('cordova');
	
	
function BetaoutWrapper(){
	
	cordova.addWindowEventHandler('deeplink');
	
	channel.onCordovaReady.subscribe(function(){
		exec(betaoutWrapper._status, betaoutWrapper._error, "BetaoutWrapper", "startDeeplinkReceiver", []); 
	});

}

BetaoutWrapper.prototype.setCustomerId = function(customerId, success, error) {
    exec(success, error, "BetaoutWrapper", "setCustomerId", [customerId]);
};

BetaoutWrapper.prototype.setCustomerEmail = function(customerEmail, success, error) {
    exec(success, error, "BetaoutWrapper", "setCustomerEmail", [customerEmail]);
};

BetaoutWrapper.prototype.setCustomerPhone = function(customerPhone, success, error) {
    exec(success, error, "BetaoutWrapper", "setCustomerPhone", [customerPhone]);
};

BetaoutWrapper.prototype.viewProduct = function(productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories, success, error){
	exec(success, error, "BetaoutWrapper", "viewProduct", [productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories]);
};

BetaoutWrapper.prototype.addToCart = function(cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, cartDeeplinkIOS, cartDeeplinkAndroid,
		productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories, success, error){
	exec(success, error, "BetaoutWrapper", "addToCart", [cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, 		    cartDeeplinkIOS, cartDeeplinkAndroid, productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories]);
};

BetaoutWrapper.prototype.removeFromCart = function(cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, cartDeeplinkIOS, cartDeeplinkAndroid,
		productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories, success, error){
	exec(success, error, "BetaoutWrapper", "removeFromCart", [cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, 		    cartDeeplinkIOS, cartDeeplinkAndroid, productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories]);
};

BetaoutWrapper.prototype.updateCart = function(cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, cartDeeplinkIOS, cartDeeplinkAndroid,
		productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories, success, error){
	exec(success, error, "BetaoutWrapper", "updateCart", [cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, 		    cartDeeplinkIOS, cartDeeplinkAndroid, productId, productPrice, productBrand, productName, productImageUrl,
		productUrl, productQuantity, productSku, productCategories]);
};

BetaoutWrapper.prototype.clearCart = function(cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, cartDeeplinkIOS, cartDeeplinkAndroid){
	exec(success, error, "BetaoutWrapper", "clearCart", [cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, 		    cartDeeplinkIOS, cartDeeplinkAndroid]);
};

BetaoutWrapper.prototype.updateUserProperties = function(properties, success, error) {
    exec(success, error, "BetaoutWrapper", "updateUserProperties", [properties]);
};

BetaoutWrapper.prototype.incrementUserProperties = function(properties, success, error) {
    exec(success, error, "BetaoutWrapper", "incrementUserProperties", [properties]);
};

BetaoutWrapper.prototype.appendUserProperties = function(properties, success, error) {
    exec(success, error, "BetaoutWrapper", "appendUserProperties", [properties]);
};

BetaoutWrapper.prototype.readyToPurchase = function(cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, cartDeeplinkIOS, cartDeeplinkAndroid,
		products, orderId, orderTotal, orderRevenueFromCart, orderCurrency,
				orderStatus, orderMethodOfPayment, orderCouponCode, orderShippingCharges, orderTax, orderDiscount, orderShippingMethod, customProperties, success, error){
	exec(success, error, "BetaoutWrapper", "readyToPurchase", [cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, 		    cartDeeplinkIOS, cartDeeplinkAndroid, products, orderId, orderTotal, orderRevenueFromCart, orderCurrency,
				orderStatus, orderMethodOfPayment, orderCouponCode, orderShippingCharges, orderTax, orderDiscount, orderShippingMethod, customProperties]);
};

BetaoutWrapper.prototype.completePurchase = function(cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, cartDeeplinkIOS, cartDeeplinkAndroid, orderId, orderTotal, orderRevenueFromCart, orderCurrency,
				orderStatus, orderMethodOfPayment, orderCouponCode, orderShippingCharges, orderTax, orderDiscount, orderShippingMethod, customProperties, success, error){
	exec(success, error, "BetaoutWrapper", "completePurchase", [cartTotal, cartRevenue, cartCurrency, cartAbandonUrl, 		    cartDeeplinkIOS, cartDeeplinkAndroid, products, orderId, orderTotal, orderRevenueFromCart, orderCurrency,
				orderStatus, orderMethodOfPayment, orderCouponCode, orderShippingCharges, orderTax, orderDiscount, orderShippingMethod, customProperties]);
};

BetaoutWrapper.prototype.logEvent = function(event, success, error) {
    exec(success, error, "BetaoutWrapper", "logEvent", [event]);
};

BetaoutWrapper.prototype.logEvents = function(events, success, error) {
    exec(success, error, "BetaoutWrapper", "logEvents", [events]);
};

BetaoutWrapper.prototype.logout = function(success, error) {
    exec(success, error, "BetaoutWrapper", "logout", []);
};


BetaoutWrapper.prototype._error = function(e) { 
    console.log("Error initializing Deeplink : " + e); 
}; 

BetaoutWrapper.prototype._status = function (info) { 

		if(info)
			cordova.fireWindowEvent("deeplink", info); 
	
}; 



var betaoutWrapper = new BetaoutWrapper();

module.exports = betaoutWrapper;

});
