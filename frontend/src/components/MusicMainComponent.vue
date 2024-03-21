<script>
export default {
	data() {
    return {
		currentTrackIndex: 0,
		songs: [
		{
    "id": 1,
    "album": "ALBUM",
    "name": "Mr.Jacson",
    "singer": "Outkast",
    "like": false
  }
		]
    };
  },
  mounted() {
	this.fetchSongs();
  },
  computed: {
    currentTrack() {
      return this.songs[this.currentTrackIndex];
    }
  },
  methods: {
	async fetchSongs() {
      try {
        const response = await fetch('http://localhost:8090/');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        this.songs = data;
      } catch (error) {
        console.error("Fetch error: ", error);
      }
    },
    nextTrack() {
      if (this.currentTrackIndex < this.songs.length - 1) {
        this.currentTrackIndex++;
      } else {
        this.currentTrackIndex = 0; 
      }
    },
    prevTrack() {
      if (this.currentTrackIndex > 0) {
        this.currentTrackIndex--;
      } else {
        this.currentTrackIndex = this.songs.length - 1; 
      }
    },
    playPauseTrack() {
      console.log("Toggle play/pause for", this.currentTrack.name);
    },
	toggleLike(id) {
      const song = this.songs.find(s => s.id === id);
      if (song) {
        song.like = !song.like;
        this.sendLikeUpdate();
      }
    },
	async sendLikeUpdate() {
		console.log(this.songs);
		try {
			await fetch('http://localhost:8090/setLike', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(this.songs)
			});
		} catch (error) {
			console.error("Error sending like update:", error);
		}
		},
	
}
}
</script>

<template>
  <div class="container">
			<p class="logo"> OleMusic</p>
			




			<div class="content">
				<img src="../assets/Foto-S.jpg" style = "width: 99%;">
				<div class="sing" v-for="song in songs" :key="song.id">
					<div class="box">
					<i class="fa fa-play psbtn" aria-hidden="true"
						:data-name="song.dataName"
						:data-name-author="song.author"
						:data-name-song="song.name"></i>
					</div>
					<div class="box singer">
					<span class="nameSong">{{ song.name }}</span>
					</div>
					<div class="box author">
					<span class="nameAuthor">{{ song.singer }}</span>
					</div>
					<div class="box album">
					{{ song.album }}
					</div>
					<div class="box">
					<i @click="toggleLike(song.id)" class="fa fa-heart like" :class="[song.like ? 'likered' : '']" style="opacity: 1; cursor: pointer;" aria-hidden="true" :data-name-song="song.name" ></i>
					</div>
				</div>
			</div>
			<div class="player">
				<div class="box">
				<i class="fa fa-fast-backward back" aria-hidden="true" @click="prevTrack"></i>
				</div>
				<div class="box">
				<i class="fa fa-play playStop" aria-hidden="true" @click="playPauseTrack"></i>
				</div>
				<div class="box">
				<i class="fa fa-fast-forward next" aria-hidden="true" @click="nextTrack"></i>
				</div>
				<div class="box curSinger">
				<span>{{ currentTrack.singer }} - </span>
				<span>{{ currentTrack.name }}</span>
				</div>
			</div>
		</div>
</template>

<style scoped>
/*@import url('https://fonts.googleapis.com/css2?family=Annie+Use+Your+Telescope&display=swap');*/
/*@import url('https://fonts.googleapis.com/css2?family=Noto+Serif&family=Poppins&display=swap');*/


body{
	font-family: 'Annie Use Your Telescope', cursive;
	margin: 0;
	width: 100%;
	height: 100%;
	background-color: White;
	font-family: 'Poppins', sans-serif;
	font-family: 'Roboto', sans-serif;
	
}

.container {
	/*width: 900px;*/
	height: 100hv;
	background-color: White;
	margin-right: 30%;
	margin-top: 0;
	margin-bottom: 0;
	box-sizing: border-box;
}

p {
	font-size: 16px;
}


.logo {
	margin-left: 0px;
	width: 100%;
	background-color: WhiteSmoke;
	font-size: 58px;
	font-weight: normal;
	position: fixed;
	border-bottom: 1px gray solid;
	top: 0;
	margin-top: 0;
	box-shadow: 0 1px 10px DarkGrey;
}



.player {
	position: fixed;
	display: flex;

	bottom: 0;
	background-color: WhiteSmoke;
	width: 100%;
	/* padding-top: 10px; */
	border-top: 1px gray solid;
	margin-top: 0;
	box-shadow: 0 -1px 10px DarkGrey;
	padding: 10px;
}

.back, .psbtn, .next, .like, .playStop{
	border: 1px WhiteSmoke solid;
	padding: 7px;
}

.like {
	color: Silver;
}

.content {
	/*padding-top: 10px;*/
	padding-bottom: 100px;
	display: grid;
	grid-auto-rows: minmax(45px, auto);


}

.curSinger span{
	padding:  auto 0;
}

.curSinger {
	padding: 10px;
	display: inline-block;
	font-size: 22px;
	width: 100%;
}

.sing {
	/*display: flex;*/
	/*height:35px;*/
	display: grid;
	grid-template-columns: 1fr 10fr 10fr 7fr 1fr;
	/*grid-template-rows: minmax(50px, max-content);*/
	/*grid-template-rows: repeat(5, 1fr);*/
	gap: 20px;

	margin: 5px;
	padding: 10px;
	padding-right: 5px;
	background-color: WhiteSmoke;
}

.box {
	display: grid;
	grid-auto-rows: minmax(35px, max-content);

	/* width: 32px; */
	height: 32px;
	display: inline-block;
	padding: auto 0;
	margin: auto 0;
	vertical-align: center;
	margin-right: 5px;
	word-break: break-all;
}

.singer {
	display: inline-block;
	font-size: 22px;
	width: 90%;

	display: grid;
	/*grid-area: 1/2/2/5;*/
}
/*
.singer span{
	padding:  auto 0;
}
*/

.nameSong {
	font-weight: bold;
	margin-left: 10px;

	display: grid;
	grid-template-rows: minmax(50px, max-content);

}

.album {
	display: inline-block;
	font-size: 22px;
	/* width: 35%; */
	margin-left: 35%;
	text-align: center;

	display: grid;
	/*grid-area: 3/2/4/5;*/
}

.author {
	display: inline-block;
	font-size: 22px;
	width: 80%;
	margin-left: auto;
	text-align: right;
	margin-right: 20px;

	display: grid;
	/*grid-area: 2/2/3/5;*/
}

.likered{
	color: Crimson;
}

.fa fa-play psbt{
	display: grid;
	/*grid-area: 1/1/4/2;*/
}

@media screen and (max-width: 960px){
	.sing {
	display: grid;
	grid-template-columns: 1fr 10fr 10fr 7fr 1fr;
	/*grid-template-rows: minmax(50px, max-content);*/
	grid-template-rows: repeat(3, 1fr);
	gap: 20px;

	margin: 5px;
	padding: 10px;
	padding-right: 5px;
	background-color: WhiteSmoke;
	}
	.fa fa-play psbt{
		display: grid;
		grid-area: 2/1/3/2;
		align-items: center;
	}
	.author {
		display: grid;
		grid-area: 2/2/3/5;
		justify-items: center;
	}
	.album {
		display: grid;
		grid-area: 3/2/4/5;
		justify-items: center;
	}
	.singer {
		display: grid;
		grid-area: 1/2/2/5;
		justify-items: center;
	}
	.fa fa-heart like{
		display: grid;
		grid-area: 2/5/3/6;vertical-align: center;
	}
}
</style>
