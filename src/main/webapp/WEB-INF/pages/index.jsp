<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/home_page.css">
</head>
<body>
    <jsp:include page="Header.jsp"/>
    <div class="body1">
        <div class="container1">
            <div class="left_image">
                <img class="main_image" src="picture/main diamond ring.png">
                <div class="small_images">
                    <img src="picture/women-daimond ring.png">
                    <img src="picture/men-diamondring.png">
                    <img src="picture/diamondring.png" width="183" height="153">
                </div>
            </div>

            <div class="text">
                <h2>The Hexagonal Edit: A new Era of Brilliance"</h2>
                <p>
                    Elevate your collection with Ornexa's latest
                    arrival. Featuring a signature honeycomb
                    silhouette and precision-set diamonds, this
                    design merges bold architectural lines with
                    the timeless warmth of 18k gold. Modern,
                    meticulous, and made for the now
                </p>
            </div>

            <div class="slider">
                <h2>Our Signature collection</h2>
                <p>Discover Our most loved and trending jewelry pieces</p>
                <div class="inner_slider">
                    <div class="Slider_item active">
                        <img src="picture/dragon braclet.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/newari.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/earring.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/diamond_necklace.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/bangle.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/layered_necklace.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/jhumka.png">
                    </div>
                    <div class="Slider_item">
                        <img src="picture/sherpa.png">
                    </div>
                    <button class="prev_button">&#10094;</button>
                    <button class="next_button">&#10095;</button>
                </div>
            </div>

            <div class="Collection">
                <h2>A Little More Beauty</h2>
                <p>Little details that make a big difference.</p>
            </div>

            <div class="more_collection">
                <c:forEach var="p" items="${collectionProducts}" varStatus="status">
                    <div class="list ${status.index == 0 ? 'active' : ''}">
                        <div class="imagee">
                            <img src="<%=request.getContextPath()%>/picture/${p.imgUrl}">
                            <div class="content">
                                <div class="jewlery_name">ornexa</div>
                                <div class="title">More Collections</div>
                                <div class="topic">${p.name}</div>
                                <div class="about_jewlery">${p.description}</div>
                                <div class="jewelry_button">
                                    <a href="<%=request.getContextPath()%>/DescriptionServlet?id=${p.id}">
                                        <button class="product_button">Explore</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div class="small_img">
                    <div class="imagee">
                        <img src="picture/wearing_ring.png">
                        <div class="content">
                            <div class="title">Pyakhan</div>
                            <div class="des">Ring</div>
                        </div>
                    </div>
                    <div class="imagee">
                        <img src="picture/mennn.png">
                        <div class="content">
                            <div class="title">Spine-Link</div>
                            <div class="des">Bracelet</div>
                        </div>
                    </div>
                    <div class="imagee">
                        <img src="picture/women braclets1.png">
                        <div class="content">
                            <div class="title">vine</div>
                            <div class="des">braclets</div>
                        </div>
                    </div>
                    <div class="imagee">
                        <img src="picture/wearing.png">
                        <div class="content">
                            <div class="title">Emerald-Rain</div>
                            <div class="des">Jhumkas</div>
                        </div>
                    </div>
                    <div class="imagee">
                        <img src="picture/wear.png">
                        <div class="content">
                            <div class="title">Lotus Meenakari</div>
                            <div class="des">Necklace</div>
                        </div>
                    </div>
                </div>

                <div class="arrow">
                    <button id="prev">&#10094;</button>
                    <button id="next">&#10095;</button>
                </div>
            </div>
        </div>

        <div class="About_us">
            <div class="text">
                <h2>Who We Are</h2>
                <p>Ornexa is a jewellery brand that blends modern elegance with
                traditional craftsmanship. We offer beautifully designed gold,
                silver, and diamond jewellery for both men and women, with
                styles for every occasion.</p>
                <p>At our core, we celebrate individuality, culture, and timeless
                beauty through every piece.</p>
                <a href="<%=request.getContextPath()%>/AboutUsServlet">
                    <button class="read_more">Read More</button>
                </a>
            </div>
        </div>

        <div class="reviews">
            <h2>Customer Reviews</h2>
            <p>What our customers say about Ornexa</p>
        </div>

        <div class="infinite_loop">
            <div class="group">
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>ABSOLUTELY LOVE IT!</h3>
                    <p>This piece instantly became my
                        favorite. The design is so
                        minimal yet elegant, and it goes
                        perfectly with almost every outfit
                        I wear. It really adds a nice touch
                        to my overall look. Totally worth it!<br><br>
                        Aakriti on Jan 12, 2026</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ☆</p>
                    <h3>GREAT BUT SLIGHT DELAY</h3>
                    <p>The product quality is really
                        impressive and feels premium.
                        The only issue was the delivery
                        time, which was a bit longer than
                        expected. Other than that, I'm
                        happy with my purchase.<br><br>
                        Suman on Feb 05, 2023</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>JUST PERFECT!</h3>
                    <p>I was honestly surprised by
                        how beautiful this turned out
                        to be. The finishing is neat, and
                        it looks exactly like the pictures
                        shown. I've already recommended
                        it to my friends!<br><br>
                        Pratiksha on Mar 18, 2026</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>SIMPLY ELEGANT</h3>
                    <p>I really appreciate the simple
                        yet classy design. It's lightweight,
                        comfortable, and adds just the
                        right amount of charm to my
                        look. I've been wearing it
                        almost every day!<br><br>
                        Kritika on Sep 03, 2024</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ☆</p>
                    <h3>VERY NICE QUALITY</h3>
                    <p>The material feels durable
                        and well-made. The shine
                        and finishing are impressive
                        for the price. Delivery was
                        smooth, and I'm quite happy
                        with how it turned out.<br><br>
                        Rohit on Oct 11, 2025</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>HIGHLY RECOMMENDED</h3>
                    <p>This exceeded my expectations
                        in every way. The detailing is
                        beautiful, and it looks even
                        better in person. I'm definitely
                        planning to order more from
                        here soon!<br><br>
                        Manisha on Nov 25, 2023</p>
                </div>
            </div>

            <div aria-hidden class="group">
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>ABSOLUTELY LOVE IT!</h3>
                    <p>This piece instantly became my
                        favorite. The design is so
                        minimal yet elegant, and it goes
                        perfectly with almost every outfit
                        I wear. It really adds a nice touch
                        to my overall look. Totally worth it!<br><br>
                        Aakriti on Jan 12, 2026</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ☆</p>
                    <h3>GREAT BUT SLIGHT DELAY</h3>
                    <p>The product quality is really
                        impressive and feels premium.
                        The only issue was the delivery
                        time, which was a bit longer than
                        expected. Other than that, I'm
                        happy with my purchase.<br><br>
                        Suman on Feb 05, 2023</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>JUST PERFECT!</h3>
                    <p>I was honestly surprised by
                        how beautiful this turned out
                        to be. The finishing is neat, and
                        it looks exactly like the pictures
                        shown. I've already recommended
                        it to my friends!<br><br>
                        Pratiksha on Mar 18, 2026</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>SIMPLY ELEGANT</h3>
                    <p>I really appreciate the simple
                        yet classy design. It's lightweight,
                        comfortable, and adds just the
                        right amount of charm to my
                        look. I've been wearing it
                        almost every day!<br><br>
                        Kritika on Sep 03, 2024</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ☆</p>
                    <h3>VERY NICE QUALITY</h3>
                    <p>The material feels durable
                        and well-made. The shine
                        and finishing are impressive
                        for the price. Delivery was
                        smooth, and I'm quite happy
                        with how it turned out.<br><br>
                        Rohit on Oct 11, 2025</p>
                </div>
                <div class="card">
                    <p>⭐ ⭐ ⭐ ⭐ ⭐</p>
                    <h3>HIGHLY RECOMMENDED</h3>
                    <p>This exceeded my expectations
                        in every way. The detailing is
                        beautiful, and it looks even
                        better in person. I'm definitely
                        planning to order more from
                        here soon!<br><br>
                        Manisha on Nov 25, 2023</p>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

    <script>
        const slider = document.querySelector('.inner_slider');
        const slides = document.querySelectorAll('.Slider_item');

        let index = 0;

        const prevButton = document.querySelector(".prev_button");
        const nextButton = document.querySelector(".next_button");

        function updateSlider() {
            const slideWidth = slides[0].clientWidth;
            slider.scrollLeft = index * slideWidth;
            updateButtons();
        }

        function updateButtons() {
            if (index === 0) {
                prevButton.style.opacity = "0";
                prevButton.style.pointerEvents = "none";
            } else {
                prevButton.style.opacity = "1";
                prevButton.style.pointerEvents = "auto";
            }

            if (index === slides.length - 1) {
                nextButton.style.opacity = "0";
                nextButton.style.pointerEvents = "none";
            } else {
                nextButton.style.opacity = "1";
                nextButton.style.pointerEvents = "auto";
            }
        }

        nextButton.addEventListener('click', () => {
            if (index < slides.length - 1) {
                index++;
                updateSlider();
            }
        });

        prevButton.addEventListener('click', () => {
            if (index > 0) {
                index--;
                updateSlider();
            }
        });

        updateButtons();

        function toggleDropdown(id) {
            const menu = document.getElementById(id);

            document.querySelectorAll('.dropdown-content').forEach(el => {
                if (el.id !== id) el.style.display = "none";
            });

            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }

        document.addEventListener('click', function (e) {
            if (!e.target.closest('.dropdown')) {
                document.querySelectorAll('.dropdown-content').forEach(el => {
                    el.style.display = "none";
                });
            }
        });

        document.querySelectorAll('.submenu-title').forEach(title => {
            title.addEventListener('click', function () {
                this.parentElement.classList.toggle('open');
            });
        });

        document.addEventListener("DOMContentLoaded", () => {
            const slides = document.querySelectorAll('.more_collection .list');
            const thumbs = document.querySelectorAll('.small_img .imagee');
            const next = document.querySelector('#next');
            const prev = document.querySelector('#prev');

            let current = 0;

            if (!slides.length || !thumbs.length || !next || !prev) {
                console.error("Slider elements not found. Check HTML selectors.");
                return;
            }

            function showSlide(index) {
                if (index >= slides.length) index = 0;
                if (index < 0) index = slides.length - 1;

                slides.forEach(slide => slide.classList.remove('active'));
                thumbs.forEach(thumb => thumb.classList.remove('active-thumb'));

                slides[index].classList.add('active');
                thumbs[index].classList.add('active-thumb');

                current = index;
            }

            next.addEventListener('click', () => {
                showSlide(current + 1);
            });

            prev.addEventListener('click', () => {
                showSlide(current - 1);
            });

            thumbs.forEach((thumb, index) => {
                thumb.addEventListener('click', () => {
                    showSlide(index);
                });
            });

            showSlide(0);
        });
    </script>
</body>
</html>
