async function getCurrentDbConfig(configId){
  let call = await fetch('/getCurrentDbConfig', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
           'Accept': 'application/json'
       },
       body: configId,
   });

   let result = await call.json();

   if(result.error){
     document.getElementById("config" + configId).innerHTML = result.message;
   } else {
     document.getElementById("config" + configId).innerHTML = result.config.id + ' ' + result.config.applicationName + ' ' + result.config.configVersion;
   }
}

async function getNewDbConfig(configId){
  let call = await fetch('/getNewDbConfig', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
           'Accept': 'application/json'
       },
       body: configId,
   });
   let result = await call.json();

   if(result.error){
     document.getElementById("config" + configId).innerHTML = result.message;
   } else {
     document.getElementById("config" + configId).innerHTML =  result.config.id + ' ' + result.config.applicationName + ' ' + result.config.configVersion;
   }
}

