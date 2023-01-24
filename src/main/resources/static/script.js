var likElem = document.querySelectorAll('.like')
var playElem = document.querySelectorAll('.psbtn')
var playStopElem = document.querySelectorAll('.playStop')[0]
var backElem = document.querySelectorAll('.back')[0]
var nextElem = document.querySelectorAll('.next')[0]

var playing = false
var audio

var curID = 0
var curState = false

updateListLike();

function updateListLike () {
    $.ajax({
      url: "http://127.0.0.1:8080/getMusicData",
      method: "GET",
      success: function(data) {
        console.log(data)
        data.map((value, i) => {
            console.log(value)
            if (value.like === true) {
               console.log("her")
                likElem[i].classList.toggle("like")
                likElem[i].classList.toggle("likered")
           }
        })
      }
    })
}

playStopElem.addEventListener("click", function(){
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
})

backElem.addEventListener("click", function(){
	len= playElem.length - 1
	elem = document.querySelectorAll('.psbtn')
	if(curID <= len){
		if(curID === 0) {
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
	}
	
	let name = elem[curID].getAttribute('data-name')
	let author = elem[curID].getAttribute('data-name-author')
	let nameSong = elem[curID].getAttribute('data-name-song')
		
	elem = document.querySelectorAll('.curSinger')[0];
	elem.innerHTML="<span>"+author+"</span> - <span>"+nameSong+"</span>"
});


for (let i = 0; i < likElem.length; i++){
	likElem[i].addEventListener("click", function(){

		let nameSong =  likElem[i].getAttribute('data-name-song')
		let like = !likElem[i].classList.contains("likered")

        $.ajax({
          url: "http://127.0.0.1:8080/setLike?name="+nameSong+"&like=" + like,
          method: "GET",
          success: function( result ) {
		    likElem[i].classList.toggle("likered")
          }
        })
	})
}


function setData(ctx) {
	let name = ctx.getAttribute('data-name')
	let author = ctx.getAttribute('data-name-author')
	let nameSong = ctx.getAttribute('data-name-song')
		
	elem = document.querySelectorAll('.curSinger')[0]
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
		
		elem = document.querySelectorAll('.curSinger')[0]
		elem.innerHTML=""
		elem.innerHTML="<span>"+author+"</span> - <span>"+nameSong+"</span>"

		if(playing){
			if(audio.getAttribute('src') === './audio/'+name+'.mp3'){
				this.classList.add('fa-play')
				this.classList.remove('fa-pause')
				playStopElem.classList.add('fa-play')
				playStopElem.classList.remove('fa-pause')
				playing = false
			}else{
				this.classList.add('fa-play')
				this.classList.remove('fa-pause')
				playStopElem.classList.add('fa-play')
				playStopElem.classList.remove('fa-pause')
				delete audio
				playing = false
				this.classList.remove('fa-play')
				this.classList.add('fa-pause')
				playStopElem.classList.remove('fa-play')
				playStopElem.classList.add('fa-pause')
				playing = true
			}
		}else{
			if(audio != undefined){
				if(audio.getAttribute('src') === './audio/'+name+'.mp3'){
					this.classList.remove('fa-play')
					this.classList.add('fa-pause')
					playStopElem.classList.remove('fa-play')
					playStopElem.classList.add('fa-pause')
					playing = true
				}else{
					this.classList.add('fa-play')
					this.classList.remove('fa-pause')
					playStopElem.classList.add('fa-play')
					playStopElem.classList.remove('fa-pause')
					delete audio
					playing = false
					this.classList.remove('fa-play')
					this.classList.add('fa-pause')
					playStopElem.classList.remove('fa-play')
					playStopElem.classList.add('fa-pause')
					playing = true
				}
			}else{
				this.classList.remove('fa-play')
				this.classList.add('fa-pause')
				playStopElem.classList.remove('fa-play')
				playStopElem.classList.add('fa-pause')
				playing = true
			}
		}
		for (let i = 0; i < playElemTemp.length; i++){
			if (playElemTemp[i].classList.contains('fa-pause')) {
				curID = i
			}
		}
		
		len= playElem.length - 1
		if (curID < len){
			tempNext = document.querySelectorAll('.next')[0]
			tempNext.classList.add('fa-fast-forward')
		}
	})
}