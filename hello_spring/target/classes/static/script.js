var likElem = document.querySelectorAll('.like')
var playElem = document.querySelectorAll('.psbtn')
var playStopElem = document.querySelectorAll('.playStop')[0];
var backElem = document.querySelectorAll('.back')[0];
var nextElem = document.querySelectorAll('.next')[0];

var playing = false;
var audio;

var curID = 0;
var curState = false;


playStopElem.addEventListener("click", function(){
	//alert("aaaa");
	let playElemTemp = document.querySelectorAll('.psbtn')
		
	if (curID === 0) {
		playElemTemp[0].classList.toggle('fa-pause')
		playElemTemp[0].classList.toggle('fa-play')
		
		this.classList.toggle('fa-pause')
		this.classList.toggle('fa-play')
		
	} else {
		playElemTemp[curID].classList.toggle('fa-pause')
		playElemTemp[curID].classList.toggle('fa-play')

		this.classList.toggle('fa-play')
		this.classList.toggle('fa-pause')
	}
	/*if (curState != true) {
		ss = './audio/'+data[0].pathName+'.mp3'
		audio = new Audio(ss);
		audio.play();
		playing = true;
		curID = data[0].id;
		curState = true;
		curPlayElem = document.querySelectorAll('.psbtn')[0];
		
		curPlayElem.classList.remove('fa-play')
		curPlayElem.classList.add('fa-pause')
		
		this.classList.add('fa-pause')
		this.classList.remove('fa-play')
	} else {
		audio.pause();
		this.classList.add('fa-play')
		this.classList.remove('fa-pause')
				
		playing = false;
		curPlayElem = document.querySelectorAll('.psbtn')[0];
		curPlayElem.classList.remove('fa-pause')
		curPlayElem.classList.add('fa-play')
	}*/
})


backElem.addEventListener("click", function(){
	len= playElem.length - 1
	elem = document.querySelectorAll('.psbtn')
	if(curID <= len){
		if(curID === 0) {
			//tempNext = document.querySelectorAll('.back')[0];
			//tempNext.classList.remove('fa-fast-backward')
			//elem[curID].classList.toggle('fa-pause')
			//elem[curID].classList.toggle('fa-play')
		} else{
			elem[curID].classList.toggle('fa-pause')
			elem[curID].classList.toggle('fa-play')
			curID--
			elem[curID].classList.toggle('fa-pause')
			elem[curID].classList.toggle('fa-play')
		}
	} else {
	
		elem[curID].classList.toggle('fa-pause')
		elem[curID].classList.toggle('fa-play')
		//tempNext = document.querySelectorAll('.back')[0];
		//tempNext.classList.remove('fa-fast-backward')
	}
	
	let name = elem[curID].getAttribute('data-name')
	let author = elem[curID].getAttribute('data-name-author')
	let nameSong = elem[curID].getAttribute('data-name-song')
		
	elem = document.querySelectorAll('.curSinger')[0];
	elem.innerHTML="<span>"+author+"</span> - <span>"+nameSong+"</span>"
	
});

nextElem.addEventListener("click", function(){
	len= playElem.length - 1
	elem = document.querySelectorAll('.psbtn')
	if(curID < len){
		if(curID === 0) {
			elem[curID].classList.toggle('fa-pause')
			elem[curID].classList.toggle('fa-play')
			curID++
			elem[curID].classList.toggle('fa-pause')
			elem[curID].classList.toggle('fa-play')
		} else{
			elem[curID].classList.toggle('fa-pause')
			elem[curID].classList.toggle('fa-play')
				curID++
			elem[curID].classList.toggle('fa-pause')
			elem[curID].classList.toggle('fa-play')
		}
	} else {
		//elem[curID].classList.toggle('fa-pause')
		//elem[curID].classList.toggle('fa-play')
		//tempNext = document.querySelectorAll('.next')[0];
		//tempNext.classList.remove('fa-fast-forward')
	}
	
	let name = elem[curID].getAttribute('data-name')
	let author = elem[curID].getAttribute('data-name-author')
	let nameSong = elem[curID].getAttribute('data-name-song')
		
	elem = document.querySelectorAll('.curSinger')[0];
	elem.innerHTML="<span>"+author+"</span> - <span>"+nameSong+"</span>"
});


for (let i = 0; i < likElem.length; i++){
	likElem[i].addEventListener("click", function(){
		likElem[i].classList.toggle("likered");
	})
}


function setData(ctx) {
	let name = ctx.getAttribute('data-name')
		let author = ctx.getAttribute('data-name-author')
		let nameSong = ctx.getAttribute('data-name-song')
		
		elem = document.querySelectorAll('.curSinger')[0];
		elem.innerHTML=""
		elem.innerHTML="<span>"+author+"</span> - <span>"+nameSong+"</span>"
		
}

for (let i = 0; i < playElem.length; i++){
	playElem[i].addEventListener("click", function(event) {
		let playElemTemp = document.querySelectorAll('.psbtn')
		for (let i = 0; i < playElemTemp.length; i++){
			if (playElemTemp[i].classList.contains('fa-pause')) {
				curID = i
			}
			playElemTemp[i].classList.remove('fa-pause')
			playElemTemp[i].classList.add('fa-play')
		}
		let name = this.getAttribute('data-name')
		let author = this.getAttribute('data-name-author')
		let nameSong = this.getAttribute('data-name-song')
		
		elem = document.querySelectorAll('.curSinger')[0];
		elem.innerHTML=""
		elem.innerHTML="<span>"+author+"</span> - <span>"+nameSong+"</span>"
		
		//alert(curAut)
		if(playing){
			if(audio.getAttribute('src') === './audio/'+name+'.mp3'){
				//audio.pause();
				this.classList.add('fa-play')
				this.classList.remove('fa-pause')
				playStopElem.classList.add('fa-play')
				playStopElem.classList.remove('fa-pause')
				playing = false;
			}else{
				//audio.pause();
				this.classList.add('fa-play')
				this.classList.remove('fa-pause')
				playStopElem.classList.add('fa-play')
				playStopElem.classList.remove('fa-pause')
				delete audio;
				playing = false;
				//audio = new Audio('./audio/'+name+'.mp3');
				this.classList.remove('fa-play')
				this.classList.add('fa-pause')
				playStopElem.classList.remove('fa-play')
				playStopElem.classList.add('fa-pause')
				//audio.play();
				playing = true;
			}
		}else{
			//уже есть песня
			if(audio != undefined){
				if(audio.getAttribute('src') === './audio/'+name+'.mp3'){
					this.classList.remove('fa-play')
					this.classList.add('fa-pause')
					playStopElem.classList.remove('fa-play')
					playStopElem.classList.add('fa-pause')
					//audio.play();
					playing = true;
				}else{
					//audio.pause();
					this.classList.add('fa-play')
					this.classList.remove('fa-pause')
					playStopElem.classList.add('fa-play')
					playStopElem.classList.remove('fa-pause')
					delete audio;
					playing = false;
					//audio = new Audio('./audio/'+name+'.mp3');
					//audio.play();
					this.classList.remove('fa-play')
					this.classList.add('fa-pause')
					playStopElem.classList.remove('fa-play')
					playStopElem.classList.add('fa-pause')
					playing = true;
				}
			}else{
				//audio = new Audio('./audio/'+name+'.mp3');
				this.classList.remove('fa-play')
				this.classList.add('fa-pause')
				playStopElem.classList.remove('fa-play')
				playStopElem.classList.add('fa-pause')
				//audio.play();
				playing = true;
			}
		}
		for (let i = 0; i < playElemTemp.length; i++){
			if (playElemTemp[i].classList.contains('fa-pause')) {
				curID = i
			}
		}
		
		len= playElem.length - 1
		if (curID < len){
			tempNext = document.querySelectorAll('.next')[0];
			tempNext.classList.add('fa-fast-forward')
		}
	})
}