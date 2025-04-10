/*
 * Copyright (c) 2023 European Commission
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.europa.ec.eudi.statium

import eu.europa.ec.eudi.statium.misc.StatiumJson
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TestVector(
    val expectedStatuses: Map<StatusIndex, Status>,
    val statusList: StatusList,
    val statusListCborBytes: ByteArray,
) {
    constructor(expectedStatuses: Map<Int, Int>, statusListJson: String, statusListCborHex: String) : this(
        expectedStatuses.map { (i, s) -> StatusIndex(i) to Status(s.toUByte()) }.toMap(),
        StatiumJson.decodeFromString(StatusList.serializer(), statusListJson),
        statusListCborHex.hexToByteArray(HexFormat.Default),
    )
}

object TestVectors {
    val TV1 = TestVector(
        expectedStatuses = buildMap {
            put(0, 1)
            put(1993, 1)
            put(25460, 1)
            put(159495, 1)
            put(495669, 1)
            put(554353, 1)
            put(645645, 1)
            put(723232, 1)
            put(854545, 1)
            put(934534, 1)
            put(1000345, 1)
        },
        statusListJson = """
            {
              "bits": 1,
              "lst": "eNrt3AENwCAMAEGogklACtKQPg9LugC9k_ACvreiogEAAKkeCQAAAAAAAAAAAAAAAAAAAIBylgQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAXG9IAAAAAAAAAPwsJAAAAAAAAAAAAAAAvhsSAAAAAAAAAAAA7KpLAAAAAAAAAAAAAAAAAAAAAJsLCQAAAAAAAAAAADjelAAAAAAAAAAAKjDMAQAAAACAZC8L2AEb"
            }
        """.trimIndent(),
        statusListCborHex = """
            a2646269747301636c737458bd78daeddc010dc0200c0041a88249400ad2903e0f4b
            ba00bd93f002beb7a2a2010000a91e09000000000000000000000000000000807296
            04000000000000000000000000000000000000000000000000000000000000000000
            000000000000005c6f4800000000000000fc2c240000000000000000000000be1b12
            000000000000000000ecaa4b000000000000000000000000000000009b0b09000000
            00000000000038de9400000000000000002a30cc010000000080642f0bd8011b
        """.trimIndent().replace("\n", ""),
    )

    val TV2 = TestVector(
        buildMap {
            put(0, 1)
            put(1993, 2)
            put(25460, 1)
            put(159495, 3)
            put(495669, 1)
            put(554353, 1)
            put(645645, 2)
            put(723232, 1)
            put(854545, 1)
            put(934534, 2)
            put(1000345, 3)
        },
        statusListJson = """
          {
            "bits": 2,
            "lst": "eNrt2zENACEQAEEuoaBABP5VIO01fCjIHTMStt9ovGVIAAAAAABAbiEBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEB5WwIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAID0ugQAAAAAAAAAAAAAAAAAQG12SgAAAAAAAAAAAAAAAAAAAAAAAAAAAOCSIQEAAAAAAAAAAAAAAAAAAAAAAAD8ExIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAwJEuAQAAAAAAAAAAAAAAAAAAAAAAAMB9SwIAAAAAAAAAAAAAAAAAAACoYUoAAAAAAAAAAAAAAEBqH81gAQw"
          }
        """.trimIndent(),
        statusListCborHex = """
            a2646269747302636c737459013d78daeddb310d00211000412ea1a04004fe5520ed
            357c28c81d3312b6df68bc65480000000000406e2101000000000000000000000000
            0000000000000000000000000000000000000040795b020000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            0080f4ba0400000000000000000000000000406d764a000000000000000000000000
            000000000000000000e0922101000000000000000000000000000000000000fc1312
            00000000000000000000000000000000000000000000000000000000000000c0912e
            01000000000000000000000000000000000000c07d4b020000000000000000000000
            00000000a8614a0000000000000000000000406a1fcd60010c
        """.trimIndent().replace("\n", ""),
    )

    val TV4 = TestVector(
        buildMap {
            put(0, 1)
            put(1993, 2)
            put(35460, 3)
            put(459495, 4)
            put(595669, 5)
            put(754353, 6)
            put(845645, 7)
            put(923232, 8)
            put(924445, 9)
            put(934534, 10)
            put(1004534, 11)
            put(1000345, 12)
            put(1030203, 13)
            put(1030204, 14)
            put(1030205, 15)
        },
        statusListJson = """
          {
            "bits": 4,
            "lst": "eNrt0EENgDAQADAIHwImkIIEJEwCUpCEBBQRHOy35Li1EjoOQGabAgAAAAAAAAAAAAAAAAAAACC1SQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABADrsCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADoxaEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIIoCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACArpwKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGhqVkAzlwIAAAAAiGVRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABx3AoAgLpVAQAAAAAAAAAAAAAAwM89rwMAAAAAAAAAAAjsA9xMBMA"
          }

        """.trimIndent(),
        statusListCborHex = """
            a2646269747304636c737459024878daedd0410d8030100030081f0226908204244c
            025290840414111cecb7e4b8b5123a0e40669b020000000000000000000000000000
            0020b549010000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            0000000000400ebb0200000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            000000000000e8c5a100000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000000000000
            00000000000000000000000000000000000082280a00000000000000000000000000
            00000000000000000000000000000000000000000000000000000000000080ae9c0a
            00000000000000000000000000000000000000000000000000000000000000000000
            000000686a5640339702000000008865510000000000000000000000000000000000
            00000000000000000000000000000071dc0a0080ba55010000000000000000000000
            c0cf3daf03000000000000000008ec03dc4c04c0
        """.trimIndent().replace("\n", ""),
    )

    val TV8 = TestVector(
        buildMap {
            put(233478, 0)
            put(52451, 1)
            put(576778, 2)
            put(513575, 3)
            put(468106, 4)
            put(292632, 5)
            put(214947, 6)
            put(182323, 7)
            put(884834, 8)
            put(66653, 9)
            put(62489, 10)
            put(196493, 11)
            put(458517, 12)
            put(487925, 13)
            put(55649, 14)
            put(416992, 15)
            put(879796, 16)
            put(462297, 17)
            put(942059, 18)
            put(583408, 19)
            put(13628, 20)
            put(334829, 21)
            put(886286, 22)
            put(713557, 23)
            put(582738, 24)
            put(326064, 25)
            put(451545, 26)
            put(705889, 27)
            put(214350, 28)
            put(194502, 29)
            put(796765, 30)
            put(202828, 31)
            put(752834, 32)
            put(721327, 33)
            put(554740, 34)
            put(91122, 35)
            put(963483, 36)
            put(261779, 37)
            put(793844, 38)
            put(165255, 39)
            put(614839, 40)
            put(758403, 41)
            put(403258, 42)
            put(145867, 43)
            put(96100, 44)
            put(477937, 45)
            put(606890, 46)
            put(167335, 47)
            put(488197, 48)
            put(211815, 49)
            put(797182, 50)
            put(582952, 51)
            put(950870, 52)
            put(765108, 53)
            put(341110, 54)
            put(776325, 55)
            put(745056, 56)
            put(439368, 57)
            put(559893, 58)
            put(149741, 59)
            put(358903, 60)
            put(513405, 61)
            put(342679, 62)
            put(969429, 63)
            put(795775, 64)
            put(566121, 65)
            put(460566, 66)
            put(680070, 67)
            put(117310, 68)
            put(480348, 69)
            put(67319, 70)
            put(661552, 71)
            put(841303, 72)
            put(561493, 73)
            put(138807, 74)
            put(442463, 75)
            put(659927, 76)
            put(445910, 77)
            put(1046963, 78)
            put(829700, 79)
            put(962282, 80)
            put(299623, 81)
            put(555493, 82)
            put(292826, 83)
            put(517215, 84)
            put(551009, 85)
            put(898490, 86)
            put(837603, 87)
            put(759161, 88)
            put(459948, 89)
            put(290102, 90)
            put(1034977, 91)
            put(190650, 92)
            put(98810, 93)
            put(229950, 94)
            put(320531, 95)
            put(335506, 96)
            put(885333, 97)
            put(133227, 98)
            put(806915, 99)
            put(800313, 100)
            put(981571, 101)
            put(527253, 102)
            put(24077, 103)
            put(240232, 104)
            put(559572, 105)
            put(713399, 106)
            put(233941, 107)
            put(615514, 108)
            put(911768, 109)
            put(331680, 110)
            put(951527, 111)
            put(6805, 112)
            put(552366, 113)
            put(374660, 114)
            put(223159, 115)
            put(625884, 116)
            put(417146, 117)
            put(320527, 118)
            put(784154, 119)
            put(338792, 120)
            put(1199, 121)
            put(679804, 122)
            put(1024680, 123)
            put(40845, 124)
            put(234603, 125)
            put(761225, 126)
            put(644903, 127)
            put(502167, 128)
            put(121477, 129)
            put(505144, 130)
            put(165165, 131)
            put(179628, 132)
            put(1019195, 133)
            put(145149, 134)
            put(263738, 135)
            put(269256, 136)
            put(996739, 137)
            put(346296, 138)
            put(555864, 139)
            put(887384, 140)
            put(444173, 141)
            put(421844, 142)
            put(653716, 143)
            put(836747, 144)
            put(783119, 145)
            put(918762, 146)
            put(946835, 147)
            put(253764, 148)
            put(519895, 149)
            put(471224, 150)
            put(134272, 151)
            put(709016, 152)
            put(44112, 153)
            put(482585, 154)
            put(461829, 155)
            put(15080, 156)
            put(148883, 157)
            put(123467, 158)
            put(480125, 159)
            put(141348, 160)
            put(65877, 161)
            put(692958, 162)
            put(148598, 163)
            put(499131, 164)
            put(584009, 165)
            put(1017987, 166)
            put(449287, 167)
            put(277478, 168)
            put(991262, 169)
            put(509602, 170)
            put(991896, 171)
            put(853666, 172)
            put(399318, 173)
            put(197815, 174)
            put(203278, 175)
            put(903979, 176)
            put(743015, 177)
            put(888308, 178)
            put(862143, 179)
            put(979421, 180)
            put(113605, 181)
            put(206397, 182)
            put(127113, 183)
            put(844358, 184)
            put(711569, 185)
            put(229153, 186)
            put(521470, 187)
            put(401793, 188)
            put(398896, 189)
            put(940810, 190)
            put(293983, 191)
            put(884749, 192)
            put(384802, 193)
            put(584151, 194)
            put(970201, 195)
            put(523882, 196)
            put(158093, 197)
            put(929312, 198)
            put(205329, 199)
            put(106091, 200)
            put(30949, 201)
            put(195586, 202)
            put(495723, 203)
            put(348779, 204)
            put(852312, 205)
            put(1018463, 206)
            put(1009481, 207)
            put(448260, 208)
            put(841042, 209)
            put(122967, 210)
            put(345269, 211)
            put(794764, 212)
            put(4520, 213)
            put(818773, 214)
            put(556171, 215)
            put(954221, 216)
            put(598210, 217)
            put(887110, 218)
            put(1020623, 219)
            put(324632, 220)
            put(398244, 221)
            put(622241, 222)
            put(456551, 223)
            put(122648, 224)
            put(127837, 225)
            put(657676, 226)
            put(119884, 227)
            put(105156, 228)
            put(999897, 229)
            put(330160, 230)
            put(119285, 231)
            put(168005, 232)
            put(389703, 233)
            put(143699, 234)
            put(142524, 235)
            put(493258, 236)
            put(846778, 237)
            put(251420, 238)
            put(516351, 239)
            put(83344, 240)
            put(171931, 241)
            put(879178, 242)
            put(663475, 243)
            put(546865, 244)
            put(428362, 245)
            put(658891, 246)
            put(500560, 247)
            put(557034, 248)
            put(830023, 249)
            put(274471, 250)
            put(629139, 251)
            put(958869, 252)
            put(663071, 253)
            put(152133, 254)
            put(19535, 255)
        },
        statusListJson = """
          {
  "bits": 8,
  "lst": "eNrt0WOQM2kYhtGsbdu2bdu2bdu2bdu2bdu2jVnU1my-SWYm6U5enFPVf7ue97orFYAo7CQBAACQuuckAABStqUEAAAAAAAAtN6wEgAE71QJAAAAAIrwhwQAAAAAAdtAAgAAAAAAACLwkAQAAAAAAAAAAACUaFcJAACAeJwkAQAAAAAAAABQvL4kAAAAWmJwCQAAAAAAAAjAwBIAAAB06ywJoDKQBARpfgkAAAAAAAAAAAAAAAAAAACo50sJAAAAAAAAAOiRcSQAAAAAgAJNKgEAAG23mgQAAAAAAECw3pUAQvegBAAAAAAAAADduE4CAAAAyjSvBAAQiw8koHjvSABAb-wlARCONyVoxtMSZOd0CQAAAOjWDRKQmLckAAAAAACysLYEQGcnSAAAAAAQooUlAABI15kSAIH5RAIgLB9LABC4_SUgGZNIAABAmM6RoLbTJIASzCIBAEAhfpcAAAAAAABquk8CAAAAAAAAaJl9SvvzBOICAFWmkIBgfSgBAAAANOgrCQAAAAAAAADStK8EAAC03gASAAAAAAAAAADFWFUCAAAAMjOaBEADHpYAQjCIBADduFwCAAAAAGitMSSI3BUSAECOHpAA6IHrJQAAAAAAsjeVBAAAKRpVAorWvwQAAAAAAAAAkKRtJAAAAAAAgCbcLAF0bXUJAAAAoF02kYDg7CYBAAAAAEB6NpQAAAAAAAAAAAAAAEr1uQQAAF06VgIAAAAAAAAAqDaeBAAQqgMkAAAAAABogQMlAAAAAAAa87MEAAAQiwslAAAAAAAAAAAAAAAAMrOyBAAAiekv-hcsY0Sgne6QAAAAAAAgaUtJAAAAAAAAAAAAAAAAAAAAAAAAAADwt-07vjVkAAAAgDy8KgFAUEaSAAAAAJL3vgQAWdhcAgAAoBHDSUDo1pQAAACI2o4SAABZm14CALoyuwQAAPznGQkgZwdLAAAQukclAAAAAAAAAAAAgKbMKgEAAAAAAAAAAAAAAAAAAECftpYAAAAAAAAAAAAACnaXBAAAAADk7iMJAAAAAAAAAABqe00CAnGbBBG4TAIAgFDdKgFAXCaWAAAAAAAAAAAAAAAAAKAJQwR72XbGAQAAAKAhh0sAAAAAAABQgO8kAAAAAAAAAAAAACAaM0kAAAC5W0QCAIJ3mAQAxGwxCQAA6nhSAsjZBRIAANEbWQIAAAAAaJE3JACAwA0qAUBIVpKAlphbAiAPp0iQnKEkAAAAAAAgBP1KAAAAdOl4CQAAAAAAAPjLZBIAAG10RtrPm8_CAEBMTpYAAAAAAIjQYBL8z5QSAAAAAEDYPpUAACAsj0gAAADQkHMlAAjHDxIA0Lg9JQAAgHDsLQEAAABAQS6WAAAAgLjNFs2l_RgLAIAEfCEBlGZZCQAAaIHjJACgtlskAAAozb0SAAAAVFtfAgAAAAAAAAAAAAAAAAAAAAAAAKDDtxIAAAAAVZaTAKB5W0kAANCAsSUgJ0tL0GqHSNBbL0gAZflRAgCARG0kQXNmlgCABiwkAQAAAEB25pIAAAAAAAAAAAAAoFh9SwAAAAAAADWNmOSrpjFsEoaRgDKcF9Q1dxsEAAAAAAAAAAAAAAAAgPZ6SQIAAAAAAAAAgChMLgEAAAAAAAAAqZlQAsK2qQQAAAAAAAD06XUJAAAAqG9bCQAAgLD9IgEAAAAAAAAAAAAAAAAAAEBNe0gAAAAAAAAAAEBPHSEBAAAAlOZtCYA4fS8B0GFRCQAo0gISAOTgNwmC840EAAAAAAAAAAAAAAAAAAAAUJydJfjXPBIAAAAAAAAAAAAAAABk6WwJAAAAAAAAAAAAAAAAqG8UCQAAgPpOlAAAIA83SQAANWwc9HUjGAgAAAAAAACAusaSAAAAAAAAAAAAAAAAAAAAAAAAAAAAqHKVBACQjxklAAAAAAAAAKBHxpQAAAAAACBME0lAdlaUAACyt7sEAAAA0Nl0EgAAAAAAAAAAAABA-8wgAQAAAAAAAKU4SgKgUtlBAgAAAAAAAAAAgMCMLwEE51kJICdzSgCJGl2CsE0tAQAA0L11JQAAAAAAAAjUOhIAAAAAAAAAAAAAAGTqeQkAAAAAAAAAAAAAKM8SEjTrJwkAAAAAAACocqQEULgVJAAAACjDUxJUKgtKAAAAqbpRAgCA0n0mAQAAAABAGzwmAUCTLpUAAAAAAAAAAEjZNRIAAAAAAAAAAAAAAAAAAAAA8I-vJaAlhpQAAAAAAHrvzjJ-OqCuuVlLAojP8BJAr70sQZVDJYAgXS0BAAAAAAAAAAAAtMnyEgAAAAAAFONKCQAAAAAAAADorc0kAAAAAAAAgDqOlgAAAAAAAAAAAADIwv0SAAAAAAAAAAAAAADBuV0CIFVDSwAAAABAAI6RAAAAAGIwrQSEZAsJAABouRclAAAAAKDDrxIAAAA0bkkJgFiMKwEAAAAAAHQyhwRk7h4JAAAAAAAAAAAgatdKAACUYj0JAAAAAAAAAAAAQnORBLTFJRIAAAAAkIaDJAAAAJryngQAAAAAAAAAAAA98oQEAAAAAAAAAEC2zpcgWY9LQKL2kwAgGK9IAAAAAPHaRQIAAAAAAAAAAADIxyoSAAAAAAAAAAAAAADQFotLAECz_gQ1PX-B"
}
        """.trimIndent(),
        statusListCborHex = """
            a2646269747308636c73745907b078daedd1639033691886d1ac6ddbb66ddbb66ddb
            b66ddbb66ddbb68d59d4d66cbe496626e94e5e9c53d57fbb9ef7ba2b158028ec2401
            000090bae724000052b6a504000000000000b4deb0120004ef5409000000008af087
            040000000001db400200000000000022f09004000000000000000000946857090000
            80789c24010000000000000050bcbe240000005a62700900000000000008c0c01200
            000074eb2c09a032900404697e09000000000000000000000000000000a8e74b0900
            000000000000e89171240000000080024d2a0100006db79a04000000000040b0de95
            0042f7a00400000000000000ddb84e02000000ca34af0400108b0f24a078ef480040
            6fec2501108e372568c6d31264e77409000000e8d60d129098b7240000000000b2b0
            b604406727480000000010a28525000048d799120081f94402202c1f4b0010b8fd25
            2019934800004098ce91a0b6d3248012cc22010040217e970000000000006aba4f02
            00000000000068997d4afbf304e2020055a69080607d280100000034e82b09000000
            00000000d2b4af040000b4de00120000000000000000c558550200000032339a0440
            031e96004230880400ddb85c020000000068ad312488dc151200408e1e9000e881eb
            250000000000b23795040000291a55028ad6bf040000000000000090a46d24000000
            00008026dc2c01746d7509000000a05d369180e0ec260100000000407a3694000000
            00000000000000004af5b90400005d3a560200000000000000a8369e040010aa0324
            00000000006881032500000000001af3b3040000108b0b2500000000000000000000
            000032b3b204000089e92ffa172c6344a09dee90000000000020694b490000000000
            000000000000000000000000000000f0b7ed3bbe3564000000803cbc2a0140504692
            0000000092f7be040059d85c020000a011c34940e8d69400000088da8e120000599b
            5e0200ba32bb040000fce719092067074b000010ba472500000000000000000080a6
            cc2a010000000000000000000000000000409fb696000000000000000000000a7697
            0400000000e4ee230900000000000000006a7b4d0202719b0411b84c02008050dd2a
            01405c269600000000000000000000000000a00943047bd976c601000000a021874b
            0000000000005080ef2400000000000000000000201a3349000000b95b4402008277
            980400c46c31090000ea785202c8d905120000d11b590200000000689137240080c0
            0d2a01404856928096985b02200fa748909ca12400000000002004fd4a00000074e9
            7809000000000000f8cb641200006d7446dacf9bcfc200404c4e96000000000088d0
            6012fccf94120000000040d83e950000202c8f48000000d09073250008c70f1200d0
            b83d2500008070ec2d0100000040412e9600000080b8cd16cda5fd180b0080047c21
            019466590900006881e32400a0b65b24000028cdbd12000000545b5f020000000000
            00000000000000000000000000a0c3b7120000000055969300a0795b490000d080b1
            2520274b4bd06a8748d05b2f480065f951020080446d24417366960080062c240100
            00004076e69200000000000000000000a0587d4b000000000000358d98e4aba6316c
            12869180329c17d435771b0400000000000000000000000080f67a49020000000000
            000080284c2e0100000000000000a9995002c2b6a904000000000000f4e975090000
            00a86f5b09000080b0fd22010000000000000000000000000000404d7b4800000000
            00000000404f1d210100000094e66d0980387d2f01d06151090028d2021200e4e037
            0982f38d04000000000000000000000000000000509c9d25f8d73c12000000000000
            00000000000064e96c09000000000000000000000000a86f1409000080fa4e940000
            200f37490000356c1cf47523180800000000000080bac69200000000000000000000
            0000000000000000000000a872950400908f192500000000000000a047c694000000
            0000204c1349407656940000b2b7bb04000000d0d974120000000000000000000040
            fbcc2001000000000000a5384a02a052d94102000000000000000080c08c2f0104e7
            59092027734a00891a5d82b04d2d010000d0bd752500000000000008d43a12000000
            000000000000000064ea79090000000000000000000028cf121234eb270900000000
            0000a872a40450b8152400000028c35312542a0b4a000000a9ba51020080d27d2601
            00000000401b3c260140932e95000000000000000048d93512000000000000000000
            00000000000000f08faf25a025869400000000007aefce327e3aa0aeb9594b0288cf
            f01240afbd2c4195432580205d2d01000000000000000000b4c9f212000000000014
            e34a0900000000000000e8adcd24000000000000803a8e9600000000000000000000
            c8c2fd120000000000000000000000c1b95d022055434b0000000040008e91000000
            006230ad0484640b09000068b9172500000000a0c3af12000000346e490980588c2b
            0100000000007432870464ee1e090000000000000000206ad74a000094623d090000
            0000000000000042739104b4c5251200000000908683240000009af29e0400000000
            00000000003df284040000000000000040b6ce9720598f4b40a2f693002018af4800
            000000f1da4502000000000000000000c8c72a120000000000000000000000d0168b
            4b0040b3fe04353d7f81
        """.trimIndent().replace("\n", ""),
    )
}

class ReadStatusTestVectors {

    @Test
    fun testVector1() = doTestReadStatus(TestVectors.TV1)

    @Test
    fun testVector2() = doTestReadStatus(TestVectors.TV2)

    @Test
    fun testVector4() = doTestReadStatus(TestVectors.TV4)

    @Test
    fun testVector8() = doTestReadStatus(TestVectors.TV8)

    private fun doTestReadStatus(tv: TestVector) =
        runTest {
            val decompress = platformDecompress(coroutineContext)
            val readStatus = ReadStatus.fromStatusList(tv.statusList, decompress).getOrThrow()
            tv.expectedStatuses.forEach { (index, expectedStatus) ->
                val actualStatus = readStatus(index).getOrThrow()
                assertEquals(expectedStatus, actualStatus)
            }
        }
}
