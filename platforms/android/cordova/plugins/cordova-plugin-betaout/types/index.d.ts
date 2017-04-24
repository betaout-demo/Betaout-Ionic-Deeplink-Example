interface Window { 

    ondeeplink: (type: DeeplinkEvent) => void;
    
    addEventListener(type: "deeplink", listener: (ev: DeeplinkEvent) => any): void; 

    addEventListener(type: string, listener: (ev: Event) => any): void; 
    
    removeEventListener(type: "deeplink", listener: (ev: DeeplinkEvent) => any): void; 

    removeEventListener(type: string, listener: (ev: Event) => any): void; 
} 
 
 
/** Object, that passed into deeplink event listener */ 
interface DeeplinkEvent extends Event { 
 	
    deeplink: string; 
	
} 
