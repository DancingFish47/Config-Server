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

async function getNewGitConfig(){
    let call = await fetch("/getNewGitConfig", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    });
    let result = await call.json();

    if(result.error){
         document.getElementById("configGitDiv").innerHTML = result.message;
       } else {
         document.getElementById("configGitDiv").innerHTML = result.config.id + ' ' + result.config.applicationName + ' ' + result.config.configVersion;
       }

}

async function getCurrentGitConfig(){
    let call = await fetch("/getCurrentGitConfig", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });
    let result = await call.json();

    if(result.error){
         document.getElementById("configGitDiv").innerHTML = result.message;
       } else {
         document.getElementById("configGitDiv").innerHTML = result.config.id + ' ' + result.config.applicationName + ' ' + result.config.configVersion;
       }
}
