const gitConfigUrl = 'https://api.github.com/repos/DancingFish47/Config-Server/contents/config.txt';

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
    let call = await fetch(gitConfigUrl, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });
    let result = await call.json();

    if(result.content!=null){
        document.getElementById("configGitDiv").innerHTML = atob(result.content);
        cacheGitConfig(atob(result.content));
    } else {
        document.getElementById("configGitDiv").innerHTML = "Could not load config from github";
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
    let result = await call.text();

    if(result!=null){
        document.getElementById("configGitDiv").innerHTML = result;
    } else {
        document.getElementById("configGitDiv").innerHTML = "Could not load config from cache";
    }
}

async function cacheGitConfig(configJson){
    await fetch("/cacheGitConfig", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: configJson,
    });
}
