jfk.wav:         20:17:26,27 -> 20:17:42,716 = 16s
test9-09.mp3:    20:18:53,460 -> 20:26:10,148 = 7 min


Resultado do arquivo:

[French translation]
 [Music]
 Place de la Toile, the weekly magazine of digital cultures, new technologies and their uses.
 Today, we are in the laboratory of high-security IT with Jean-Yves Marion and Radou Staté.
 And then, Powerpoint with Franck Fromer. Hello everyone.
 [Music]
 In about 40 minutes, we will talk about Powerpoint, the famous Microsoft visual presentation software.
 We will not talk about Powerpoint to analyze its latest developments, but because,
 according to Franck Fromer, it would make stupid "Hello, Franck Fromer."
 You have just published the "Thought Powerpoint" investigation on this software that makes stupid the book in which
 you show how Powerpoint ceases to be just a tool for presentation to become a way to represent the world,
 and at the same time, a way to represent solutions to the problems of the world,
 which can be quite worrying when these problems are a state reform, the RGPP in France or the war in Afghanistan.
 In short, the "Thought Powerpoint" will be with you in about 40 minutes,
 but in the meantime, you can obviously intervene, as you wish, in this first part of Place de la Toile.
 [Music]
 A high-security IT laboratory opened its doors in July in Nancy, in the Inria Center.
 So it might seem trivial, there are many IT security laboratories,
 except that this one is not like the others, like those owned by the military or private companies specialized in IT security.
 This laboratory is an academic laboratory, a university laboratory, and it is the first of this kind in France,
 which is in itself a strangeness that deserves to be questioned, but beyond, there are questions that we all ask ourselves
 when we talk about a place like this. What does a computer security laboratory look like?
 What are the research fields? What tools are available to researchers?
 But it is also the occasion, when we talk with Stuxnet, which has touched on industrial infrastructures,
 by going from a new generation of viruses, it is the opportunity to make a small point
 on the latest developments in computer virology.
 We will talk about all this this morning with two guests. Jean-Yves Marion, hello.
 Hello.
 You run this high-security IT laboratory and you specialize in virology, the study of IT viruses.
 Radostat, hello.
 Hello.
 You are a colleague, well, you are a subordinate, no? I don't know.
 Colleague.
 Colleague, that's nice, like the boss.
 You are a professor at the university specialized in IT security and you work within the team
 which is in charge of the vulnerability of communicating systems, is that it?
 Yes, the automatic detection of vulnerabilities of communicating systems, the MADIN team, Alain Rialoren.
 The MADIN team, so we will come, you will detail exactly what you do and how you work.
 I started by saying that it was the first laboratory of this kind in France.
 Jean-Yves Marion, why is there no other laboratory of this type, academic, academic, on IT security?
 Why, it's hard to say. One of the reasons may be that the security problem is really beginning to appear.
 There are a lot of research that are done in the academic and university environment.
 They are theoretical and fundamental research.
 And then now we really need to do experiments and we need a platform to lead to these experiments safely.
 It's also because it poses other complicated problems.
 I was thinking for example about the publication of your research.
 You are university students, so the validation of your research is part of their publication.
 When we work as a radio-stated on the detection of vulnerabilities in communicating systems,
 I don't know, when we realize that such a computer, such a mobile phone has a huge flaw
 and that to validate this research, it would theoretically be necessary to publish it, it poses problems.
 You are right, that's where the creation of LHS comes from.
 LHS is a regulatory framework to be able to do these experiments and publish on these experiments.
 So the type of experiments that we lead are either the experiments that Radu will explain to us in a moment,
 or we will, on our side, at the level of virology, mount attacks.
 Obviously, we can't make attacks in an open environment because it would be annoying for the university to answer to a net bot, for example.
 It never happened? It has already happened.
 Before having the security center, we did experiments with Storm.
 We did it, we did it in a way that we controlled, but we were still spotted by a certain Frenchman.
 And we had the need for explanations to return.
 So it justifies the interest in having a security center, which we have now.
 So completely closed?
 LHS is a double protection, a protection in relation to us.
 We don't make manipulation errors that would allow us to release a virus or a botnet.
 And on the other hand, it's this framework that actually allows us to publish.
 That's important.
 And also the fact of being able to do experiments, to do, to dialogue or to do real experiments on a large scale for companies.
 It just looks like a physical high-tech computer security laboratory.
 There's a little movie that's on Dailymotion, YouTube, where you're seen.
 It's very spectacular, because it's a bit like the Jack Bauer's City You in the 24-hour series.
 You need biometric stuff to get in, etc.
 But I imagine it's a bit staged.
 Is it that spectacular?
 Yes, there is a staging. It was very fun to make this film.
 In fact, these three rooms are located in the basement of a university lab called L'Orient.
 You enter through a biometric system where you put your finger on a little red light that flashes.
 And then there are three rooms that are secured with distinct levels of security.
 There is a large convivial research room with the main research instruments,
 that is, a coffee machine, a large table and then some sofa to be able to discuss and imagine a lot of things.
 And next to it, there are two rooms that are protected by biometric access,
 where you will find the computing center, the machines to calculate.
 And then a room that we call our red zone, to have fun, where we store confidential data,
 such as the basis of the netvirus or traces that we recover on the Internet and whose access is obviously restricted.
 I come back to this question of an academic or university laboratory.
 How are your researches distinguished from those that are carried out in the two major traditional research sites in security,
 which are the military laboratories on one side and the private laboratories on the other,
 companies that are specialized in computer security?
 How are your researches distinguished from those that are carried out in these two places?
 The first huge distinction is that our goal is really to take the fundamental research and to experiment it,
 which is not the case of the Ministry of Defense or the industry.
 So I think that's really important.
 So we're trying to bring new ideas, to show that these ideas, which are a priori theoretical, can work
 and lead to new results and then in the end, new systems against viruses or new parades, new protections.
 But is it interesting in fundamental research, the virus or the flaws in communication systems?
 Strangely, virology is something that is born at the same time as computer science.
 The first results that virology can be called date from the years 35, 36.
 There are not even computers.
 These are great scientists like Alan Turing, Fonderman and others who really developed these ideas.
 They didn't call it a virus because there were not even computers.
 But it's the basis of our work.
 And then there are different works, but you have to wait until the 80s, 90s for research that can be qualified as scientific in this field.
 There are really difficult subjects, scientific viruses that are hard to solve.
 But what are they? Algorithm? What are they? Are they maths?
 Yes, they are maths, algorithms, a lot of algorithms, but there is also the notion of what a program is,
 because mal-bearing programs are extremely sophisticated, in particular as they have camouflage tools,
 I don't think we can detect them.
 We don't have access to their source code, that is to say what they do.
 So you have to try to analyze them through tools, so you have to create these tools and that's not easy.
 So, yes, to start.
 The second difference is that you see we have interns, we have students who do masters,
 [SPEAKING FRENCH]
 who work on subjects that are at the center of the security laboratory.
