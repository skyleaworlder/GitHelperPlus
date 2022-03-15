package dev.skyleaworlder.githelperplus

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.junit.Test
import java.io.File
import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter

class DiffGetterTest {
    private val diffGetter by lazy {
        val repoPath = "D:\\tmp\\Telephony\\.git"
        val repository: Repository = PrepareUtil.makeRepository(repoPath)
        DiffGetter(repository)
    }
    private val logOutputPath = "D:\\tmp\\DiffGetterTest.log"
    private val logger = Logger.getLogger("DiffGetterTest")

    init {
        val fhd = FileHandler(logOutputPath)
        logger.addHandler(fhd)
        fhd.formatter = SimpleFormatter()
    }

    @Test
    fun getTwoRevCommitDiffString() {
        val newCommitId = "7be6599cbdfecbe0c01438478492deb015846fa8"
        val oldCommitId = "2e349325beda37f33d2c1e29b152cabee4e62661"
        val result: String = diffGetter.getDiff(newCommitId, oldCommitId)
        logger.info(result)
    }
}