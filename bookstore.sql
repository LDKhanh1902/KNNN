-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 25, 2025 lúc 11:55 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bookstore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `author`
--

CREATE TABLE `author` (
  `AuthorId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `BirthDate` date DEFAULT '1999-01-01',
  `Nationality` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `author`
--

INSERT INTO `author` (`AuthorId`, `Name`, `BirthDate`, `Nationality`) VALUES
(1, 'Nguyễn Văn A', '1980-05-12', 'Việt Nam'),
(2, 'Trần Thị B', '1990-06-25', 'Việt Nam'),
(3, 'Phạm Minh C', '1975-02-20', 'Việt Nam'),
(4, 'Lê Quang D', '1985-11-05', 'Việt Nam'),
(5, 'Hoàng Thanh E', '1992-08-14', 'Việt Nam'),
(6, 'Nguyễn Thiên F', '1983-03-18', 'Việt Nam'),
(7, 'Bùi Thị G', '1979-04-02', 'Việt Nam'),
(8, 'Võ Minh H', '1987-01-11', 'Việt Nam'),
(9, 'Phan Tiến I', '1995-12-30', 'Việt Nam'),
(10, 'Đỗ Hà J', '1988-09-22', 'Việt Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `BookId` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `PublisherId` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `PublicationDate` date NOT NULL,
  `Price` decimal(10,0) NOT NULL,
  `AuthorId` int(11) NOT NULL,
  `EntryDate` date NOT NULL,
  `PurchasePrice` decimal(10,0) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`BookId`, `Title`, `PublisherId`, `CategoryId`, `PublicationDate`, `Price`, `AuthorId`, `EntryDate`, `PurchasePrice`, `Quantity`) VALUES
(1, 'Tiếng Việt 123', 1, 5, '2020-01-15', 150000, 1, '2020-01-16', 100000, 45),
(2, 'Kinh Tế Học Cơ Bản', 2, 6, '2019-07-10', 250000, 2, '2019-07-11', 200000, 30),
(3, 'Lịch Sử Việt Nam', 3, 3, '2021-05-23', 180000, 3, '2021-05-24', 150000, 40),
(4, 'Cuộc Sống Tươi Đẹp', 4, 4, '2018-09-11', 200000, 4, '2018-09-12', 180000, 20),
(5, 'Kỹ Năng Thuyết Trình', 5, 7, '2020-03-15', 220000, 5, '2020-03-16', 170000, 10),
(6, 'Giới Thiệu Tâm Lý Học', 6, 8, '2020-08-14', 200000, 6, '2020-08-15', 160000, 25),
(7, 'Làm Giàu Không Khó', 7, 6, '2021-01-12', 250000, 7, '2021-01-13', 230000, 50),
(8, 'Nhật Ký Của Một Tên Tội Phạm', 8, 2, '2020-11-20', 170000, 8, '2020-11-21', 140000, 30),
(9, 'Văn Hóa Dân Tộc Việt', 9, 9, '2019-12-05', 220000, 9, '2019-12-06', 200000, 60),
(10, 'Nấu Ăn Với Bếp Việt', 10, 10, '2020-06-11', 150000, 10, '2020-06-12', 130000, 70),
(11, 'Phát Triển Bản Thân', 1, 15, '2021-02-01', 180000, 1, '2021-02-02', 150000, 20),
(12, 'Tâm Lý Học Và Cuộc Sống', 2, 7, '2020-07-18', 200000, 2, '2020-07-19', 170000, 30),
(13, 'Cuộc Sống Tươi Đẹp Mỗi Ngày', 3, 8, '2019-10-25', 170000, 3, '2019-10-26', 140000, 40),
(14, 'Du Lịch Việt Nam', 4, 9, '2018-12-30', 250000, 4, '2018-12-31', 220000, 5),
(15, 'Nghệ Thuật Làm Việc Nhóm', 5, 6, '2019-05-15', 210000, 5, '2019-05-16', 180000, 40),
(16, 'Sức Khỏe Tốt Mỗi Ngày', 6, 13, '2020-09-25', 230000, 6, '2020-09-26', 200000, 40),
(17, 'Văn Hóa Ứng Xử', 7, 14, '2019-11-30', 190000, 7, '2019-12-01', 160000, 55),
(18, 'Cách Nấu Món Ăn Việt', 8, 10, '2021-04-14', 160000, 8, '2021-04-15', 140000, 75),
(19, 'Tổng Quan Kinh Tế Việt Nam', 9, 6, '2021-06-05', 250000, 9, '2021-06-06', 230000, 20),
(20, 'Kỹ Năng Đọc Hiểu', 10, 5, '2021-07-01', 200000, 10, '2021-07-02', 180000, 20);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `CategoryId` int(11) NOT NULL,
  `CategoryName` varchar(50) NOT NULL,
  `Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`, `Description`) VALUES
(1, 'Tiểu thuyết', 'Sách kể về các câu chuyện hư cấu với nhân vật và tình tiết phong phú'),
(2, 'Khoa học', 'Sách cung cấp kiến thức về các lĩnh vực khoa học tự nhiên và xã hội'),
(3, 'Lịch sử', 'Sách kể về các sự kiện lịch sử trong nước và thế giới'),
(4, 'Văn học', 'Sách văn học, thơ ca, và các tác phẩm nghệ thuật'),
(5, 'Giáo dục', 'Sách giáo khoa và tài liệu học tập cho mọi cấp học'),
(6, 'Kinh tế', 'Sách về các khái niệm, lý thuyết và thực tiễn kinh tế'),
(7, 'Tâm lý', 'Sách phân tích các hiện tượng tâm lý và hành vi con người'),
(8, 'Kỹ năng sống', 'Sách về các kỹ năng trong cuộc sống và công việc'),
(9, 'Du lịch', 'Sách chia sẻ kinh nghiệm du lịch và khám phá thế giới'),
(10, 'Nấu ăn', 'Sách hướng dẫn các món ăn và công thức nấu bếp'),
(11, 'Tâm linh', 'Sách về các tín ngưỡng, tôn giáo và tâm linh'),
(12, 'Truyện tranh', 'Sách truyện tranh và các thể loại hình ảnh'),
(13, 'Sức khỏe', 'Sách về các phương pháp chăm sóc sức khỏe và thể dục'),
(14, 'Văn hóa', 'Sách giới thiệu các phong tục, tập quán và văn hóa các quốc gia'),
(15, 'Phát triển bản thân', 'Sách giúp phát triển các kỹ năng và tư duy cá nhân');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `EmployeeId` int(11) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `BirthDate` date NOT NULL,
  `HireDate` date NOT NULL,
  `Email` varchar(100) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `PositionId` int(11) NOT NULL,
  `Coefficient` float NOT NULL DEFAULT 1.7,
  `Password` varchar(255) NOT NULL DEFAULT 'password123'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`EmployeeId`, `FirstName`, `LastName`, `BirthDate`, `HireDate`, `Email`, `PhoneNumber`, `Address`, `PositionId`, `Coefficient`, `Password`) VALUES
(1, 'Nguyễn', 'Văn A', '1985-06-15', '2020-03-01', 'nguyen.a@example.com', '1234567890', '123 Đường ABC, TP.HCM', 1, 1.7, 'DGYYLscQhABl66pHxebOkA=='),
(2, 'Trần', 'Thị B', '1990-04-22', '2021-01-12', 'tran.b@example.com', '0902345678', '456 Đường XYZ, Hà Nội', 2, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(3, 'Lê', 'Quang C', '1988-09-18', '2020-06-15', 'le.c@example.com', '0903456789', '789 Đường DEF, Đà Nẵng', 3, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(4, 'Phạm', 'Minh D', '1995-11-05', '2021-05-10', 'pham.d@example.com', '0904567890', '123 Đường GHI, TP.HCM', 2, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(5, 'Bùi', 'Thị E', '1983-03-02', '2020-04-18', 'bui.e@example.com', '0905678901', '456 Đường JKL, Hà Nội', 1, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(6, 'Võ', 'Tiến F', '1992-08-14', '2021-02-25', 'vo.f@example.com', '0906789012', '789 Đường MNO, TP.HCM', 2, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(7, 'Nguyễn', 'Thiên G', '1991-01-30', '2021-07-03', 'nguyen.g@example.com', '0907890123', '321 Đường PQR, Đà Nẵng', 3, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(8, 'Đỗ', 'Hà H', '1994-12-11', '2020-09-09', 'do.h@example.com', '0908901234', '654 Đường STU, TP.HCM', 1, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(9, 'Phan', 'Tiến I', '1993-07-23', '2021-03-19', 'phan.i@example.com', '0909012345', '987 Đường VWX, Hà Nội', 2, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ=='),
(10, 'Lê', 'Hương J', '1996-05-10', '2020-12-14', 'le.j@example.com', '0900123456', '654 Đường YZA, Đà Nẵng', 1, 1.7, 'GJKWAPZT3XuSPCme8f8GRQ==');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `paydetails`
--

CREATE TABLE `paydetails` (
  `id` int(11) NOT NULL,
  `BookId` int(11) NOT NULL,
  `PaymentId` varchar(10) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `paydetails`
--

INSERT INTO `paydetails` (`id`, `BookId`, `PaymentId`, `Quantity`, `price`) VALUES
(1, 1, 'PAY0000001', 2, 250000),
(2, 2, 'PAY0000001', 1, 200000),
(3, 3, 'PAY0000001', 3, 300000),
(4, 4, 'PAY0000001', 1, 150000),
(5, 5, 'PAY0000001', 5, 500000),
(6, 6, 'PAY0000002', 2, 250000),
(7, 7, 'PAY0000002', 3, 200000),
(8, 8, 'PAY0000002', 1, 150000),
(9, 9, 'PAY0000002', 2, 270000),
(10, 10, 'PAY0000002', 4, 280000),
(11, 11, 'PAY0000003', 1, 350000),
(12, 12, 'PAY0000003', 3, 330000),
(13, 13, 'PAY0000003', 5, 500000),
(14, 14, 'PAY0000003', 2, 240000),
(15, 15, 'PAY0000003', 2, 270000),
(16, 16, 'PAY0000004', 1, 220000),
(17, 17, 'PAY0000004', 3, 400000),
(18, 18, 'PAY0000004', 4, 500000),
(19, 19, 'PAY0000004', 5, 420000),
(20, 20, 'PAY0000004', 2, 350000),
(21, 1, 'PAY0000005', 3, 330000),
(22, 2, 'PAY0000005', 5, 400000),
(23, 3, 'PAY0000005', 1, 280000),
(24, 4, 'PAY0000005', 3, 320000),
(25, 5, 'PAY0000005', 4, 450000),
(26, 6, 'PAY0000006', 3, 380000),
(27, 7, 'PAY0000006', 2, 370000),
(28, 8, 'PAY0000006', 1, 250000),
(29, 9, 'PAY0000006', 2, 300000),
(30, 10, 'PAY0000006', 5, 450000),
(31, 11, 'PAY0000007', 3, 380000),
(32, 12, 'PAY0000007', 4, 400000),
(33, 13, 'PAY0000007', 1, 500000),
(34, 14, 'PAY0000007', 2, 550000),
(35, 15, 'PAY0000007', 3, 470000),
(36, 16, 'PAY0000008', 4, 450000),
(37, 17, 'PAY0000008', 5, 500000),
(38, 18, 'PAY0000008', 2, 390000),
(39, 19, 'PAY0000008', 1, 300000),
(40, 20, 'PAY0000008', 4, 420000),
(41, 1, 'PAY0000009', 2, 370000),
(42, 2, 'PAY0000009', 3, 350000),
(43, 3, 'PAY0000009', 4, 400000),
(44, 4, 'PAY0000009', 1, 300000),
(45, 5, 'PAY0000009', 2, 420000),
(46, 6, 'PAY0000010', 3, 440000),
(47, 7, 'PAY0000010', 4, 500000),
(48, 8, 'PAY0000010', 1, 320000),
(49, 9, 'PAY0000010', 5, 490000),
(50, 10, 'PAY0000010', 2, 360000),
(51, 11, 'PAY0000011', 1, 280000),
(52, 12, 'PAY0000011', 4, 460000),
(53, 13, 'PAY0000011', 2, 350000),
(54, 14, 'PAY0000011', 3, 390000),
(55, 15, 'PAY0000011', 5, 500000),
(56, 16, 'PAY0000012', 1, 320000),
(57, 17, 'PAY0000012', 2, 450000),
(58, 18, 'PAY0000012', 4, 510000),
(59, 19, 'PAY0000012', 3, 470000),
(60, 20, 'PAY0000012', 1, 300000),
(61, 1, 'PAY0000013', 3, 320000),
(62, 2, 'PAY0000013', 5, 400000),
(63, 3, 'PAY0000013', 1, 450000),
(64, 4, 'PAY0000013', 2, 500000),
(65, 5, 'PAY0000013', 3, 470000),
(66, 6, 'PAY0000014', 4, 420000),
(67, 7, 'PAY0000014', 5, 500000),
(68, 8, 'PAY0000014', 1, 400000),
(69, 9, 'PAY0000014', 2, 350000),
(70, 10, 'PAY0000014', 4, 390000),
(71, 11, 'PAY0000015', 3, 470000),
(72, 12, 'PAY0000015', 2, 440000),
(73, 13, 'PAY0000015', 5, 510000),
(74, 14, 'PAY0000015', 1, 450000),
(75, 15, 'PAY0000015', 3, 470000),
(76, 16, 'PAY0000016', 4, 500000),
(77, 17, 'PAY0000016', 5, 520000),
(78, 18, 'PAY0000016', 2, 480000),
(79, 19, 'PAY0000016', 3, 460000),
(80, 20, 'PAY0000016', 4, 530000),
(81, 1, 'PAY0000017', 2, 410000),
(82, 2, 'PAY0000017', 3, 470000),
(83, 3, 'PAY0000017', 4, 450000),
(84, 4, 'PAY0000017', 5, 520000),
(85, 5, 'PAY0000017', 2, 490000),
(86, 6, 'PAY0000018', 3, 440000),
(87, 7, 'PAY0000018', 4, 460000),
(88, 8, 'PAY0000018', 5, 530000),
(89, 9, 'PAY0000018', 2, 420000),
(90, 10, 'PAY0000018', 1, 380000),
(91, 11, 'PAY0000019', 5, 500000),
(92, 12, 'PAY0000019', 3, 470000),
(93, 13, 'PAY0000019', 4, 520000),
(94, 14, 'PAY0000019', 5, 510000),
(95, 15, 'PAY0000019', 2, 430000),
(96, 16, 'PAY0000020', 3, 490000),
(97, 17, 'PAY0000020', 4, 450000),
(98, 18, 'PAY0000020', 5, 520000),
(99, 19, 'PAY0000020', 2, 480000),
(100, 20, 'PAY0000020', 3, 460000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `PaymentId` varchar(10) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `PaymentDate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `payment`
--

INSERT INTO `payment` (`PaymentId`, `Amount`, `PaymentDate`) VALUES
('PAY0000001', 500000, '2025-01-15 10:00:00'),
('PAY0000002', 450000, '2025-02-18 10:00:00'),
('PAY0000003', 550000, '2025-03-20 10:00:00'),
('PAY0000004', 400000, '2025-04-25 10:00:00'),
('PAY0000005', 300000, '2025-05-10 10:00:00'),
('PAY0000006', 600000, '2025-06-15 10:00:00'),
('PAY0000007', 650000, '2025-07-05 10:00:00'),
('PAY0000008', 700000, '2025-08-25 10:00:00'),
('PAY0000009', 720000, '2025-09-10 10:00:00'),
('PAY0000010', 750000, '2025-10-20 10:00:00'),
('PAY0000011', 500000, '2025-11-05 10:00:00'),
('PAY0000012', 800000, '2025-12-25 10:00:00'),
('PAY0000013', 530000, '2025-01-25 10:00:00'),
('PAY0000014', 470000, '2025-02-10 10:00:00'),
('PAY0000015', 550000, '2025-03-01 10:00:00'),
('PAY0000016', 450000, '2025-04-30 10:00:00'),
('PAY0000017', 600000, '2025-05-01 10:00:00'),
('PAY0000018', 350000, '2025-06-12 10:00:00'),
('PAY0000019', 460000, '2025-07-16 10:00:00'),
('PAY0000020', 700000, '2025-08-07 10:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `position`
--

CREATE TABLE `position` (
  `PositionId` int(11) NOT NULL,
  `PositionName` varchar(100) NOT NULL,
  `Salary` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `position`
--

INSERT INTO `position` (`PositionId`, `PositionName`, `Salary`) VALUES
(1, 'Quản lý cửa hàng', 10000000.00),
(2, 'Nhân viên bán hàng', 8000000.00),
(3, 'Thủ kho', 7500000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `publisher`
--

CREATE TABLE `publisher` (
  `PublisherId` int(11) NOT NULL,
  `PublisherName` varchar(100) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `publisher`
--

INSERT INTO `publisher` (`PublisherId`, `PublisherName`, `Address`, `Contact`) VALUES
(1, 'Nhà xuất bản Trẻ', '202 Lý Tự Trọng, TP.HCM', '0283829392'),
(2, 'Nhà xuất bản Kim Đồng', '37 Nguyễn Văn Cừ, Hà Nội', '0243827382'),
(3, 'Nhà xuất bản Phụ nữ', '58 Lý Thường Kiệt, Hà Nội', '0243939494'),
(4, 'Nhà xuất bản Hồng Đức', '1 Hoàng Hoa Thám, Hà Nội', '0243818181'),
(5, 'Nhà xuất bản Đại học Quốc gia', '144 Xuân Thủy, TP.HCM', '0283938483'),
(6, 'Nhà xuất bản Lao động', '11 Ngô Sĩ Liên, TP.HCM', '0283853365'),
(7, 'Nhà xuất bản Thanh niên', '21 Lê Quang Định, Hà Nội', '0243737373'),
(8, 'Nhà xuất bản Chính trị Quốc gia', '58 Hồ Tùng Mậu, TP.HCM', '0283898181'),
(9, 'Nhà xuất bản Văn học', '35 Phố Huế, Hà Nội', '0243654365'),
(10, 'Nhà xuất bản Sự thật', '99 Nguyễn Thị Minh Khai, TP.HCM', '0283746575');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`AuthorId`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`BookId`),
  ADD KEY `FK_Book_Category` (`CategoryId`),
  ADD KEY `FK_Book_Publisher` (`PublisherId`),
  ADD KEY `FK_Book_Author` (`AuthorId`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmployeeId`),
  ADD KEY `FK_Position_Employee` (`PositionId`);

--
-- Chỉ mục cho bảng `paydetails`
--
ALTER TABLE `paydetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_PayDetailst_Book` (`BookId`),
  ADD KEY `FK_PayDetailst_Payment` (`PaymentId`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentId`);

--
-- Chỉ mục cho bảng `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`PositionId`);

--
-- Chỉ mục cho bảng `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`PublisherId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `author`
--
ALTER TABLE `author`
  MODIFY `AuthorId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `BookId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `CategoryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `paydetails`
--
ALTER TABLE `paydetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT cho bảng `position`
--
ALTER TABLE `position`
  MODIFY `PositionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `publisher`
--
ALTER TABLE `publisher`
  MODIFY `PublisherId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FK_Book_Author` FOREIGN KEY (`AuthorId`) REFERENCES `author` (`AuthorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Book_Category` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Book_Publisher` FOREIGN KEY (`PublisherId`) REFERENCES `publisher` (`PublisherId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_Position_Employee` FOREIGN KEY (`PositionId`) REFERENCES `position` (`PositionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `paydetails`
--
ALTER TABLE `paydetails`
  ADD CONSTRAINT `FK_PayDetailst_Book` FOREIGN KEY (`BookId`) REFERENCES `book` (`BookId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PayDetailst_Payment` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`PaymentId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
