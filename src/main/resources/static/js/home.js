TweenMax.set("#soccer1", { opacity: 1 });

// 메인 페이지 애니메이션
const backLines = anime({
	targets: ".soccer1_extra-line > *",
	strokeDashoffset: [anime.setDashoffset, 0],
	easing: "easeInOutSine",
	duration: 500,
	delay: function (el, i) {
		return 1000 + i * 50;
	},
	autoplay: false
});
const bodyLines = anime({
	targets: ".soccer1_line > *",
	strokeDashoffset: [anime.setDashoffset, 0],
	easing: "easeInOutSine",
	duration: 500,
	delay: function (el, i) {
		return 1000 + i * 20;
	},
	autoplay: false
});
const ballLines = anime({
	targets: ".soccer1ball > .soccer1ball-line > *",
	strokeDashoffset: [anime.setDashoffset, 0],
	easing: "easeInOutSine",
	duration: 500,
	delay: function (el, i) {
		return 1000 + i * 140;
	},
	autoplay: false
});
function step1_ballTL() {
	const ball = new TimelineMax({
		onStart: function () {
			ballLines.play();
		}
	});
	ball
		.staggerFromTo(
			".soccer1ball > g:nth-child(1) > *",
			0.5,
			{ scale: 0 },
			{ scale: 1 },
			0.2
		)
	return ball;
}
function step1_backTL() {
	const back = new TimelineMax({
		onStart: function () {
			backLines.play();
		},
		onComplete: function () {
			console.log("completed");
			backLines.play();
		}
	});

	back.staggerFromTo(
		".soccer1_extra-line > g",
		1,
		{ x: -3500, rotation: -1000, transformOrigin: "50% 50%" },
		{ x: 0, rotation: 0, ease: Power4.easeOut },
		0.5
	);
	return back;
}
function step1_bodyTL() {
	const timeline = new TimelineMax({
		ease: Expo.easeOut,
		onStart: bodyLines.play(),
		onComplete: function () { }
	});

	var duration = 0.3;
	var stagger = 0.03;

	timeline.staggerFromTo(
		".soccer1_fill > *",
		duration,
		{ x: -4500 },
		{ x: 0 },
		stagger
	);

	return timeline;
}

const mainTL = new TimelineMax({});

function init() {
	mainTL
		.add(step1_bodyTL(), "step1")
		.add(step1_backTL(), "step1")
		.add(step1_ballTL(), "step1")
}

init();

// 캐러셀
$('.owl-carousel-banner').owlCarousel({
	loop: true,
	margin: 0,
	nav: false,
	// navText:
	// 	'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(-1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>',
	// 	'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>'
	// ],
	autoplay: true,
	autoplayTimeout: 10000,
	autoplayHoverPause: false,
	responsive: {
		0: {
			items: 1
		},
		600: {
			items: 1
		},
		100: {
			items: 1
		}
	}
})
