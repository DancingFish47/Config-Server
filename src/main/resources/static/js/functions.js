async function getCurrentDbConfig(){

  let call = await fetch('/getCurrentConfig', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
           'Accept': 'application/json'
       }
   });

   let result = await call.json();

   if(result.error){
     document.getElementById("configDbDiv").innerHTML = result.message;
   } else {
     document.getElementById("configDbDiv").innerHTML = result.config.id + ' ' + result.config.name + ' ' + result.config.version;
   }
}

async function getNewDbConfig(){
  let call = await fetch('/getNewDbConfig', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
           'Accept': 'application/json'
       }
   });
   let result = await call.json();

   if(result.error){
     document.getElementById("configDbDiv").innerHTML = result.message;
   } else {
     document.getElementById("configDbDiv").innerHTML = result.config.id + ' ' + result.config.name + ' ' + result.config.version;
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
         document.getElementById("configGitDiv").innerHTML = result.config.id + ' ' + result.config.name + ' ' + result.config.version;
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
         document.getElementById("configGitDiv").innerHTML = result.config.id + ' ' + result.config.name + ' ' + result.config.version;
       }
}
