class StringGenerator {

    companion object {
        private const val bytesPerMb = 1048576

        fun withSizeInMegaBytes(nMBytes: Double): String {
            var s = "a"
            val initSize = s.toByteArray().size
            val repetitions = ((nMBytes * bytesPerMb) / initSize).toLong()

            var count = 0
            while (count < repetitions) {
                s += "a"
                ++count
            }

            return s
        }
    }
}
